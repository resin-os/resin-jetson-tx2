SUMMARY = "Prepare bsp binaries for flashing"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${RESIN_COREBASE}/COPYING.Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "virtual/bootloader virtual/kernel tegra-binaries"

inherit deploy
SRC_URI = " \
    file://flash.xml \
    file://partition_specification.txt \
    "

SHARED = "${TMPDIR}/work-shared/L4T-${SOC_FAMILY}-${PV}-${PR}/Linux_for_Tegra"
B = "${WORKDIR}/build"
S = "${WORKDIR}"

DTB_jetson-tx2 = "${SHARED}/kernel/dtb/tegra186-quill-p3310-1000-c03-00-base.dtb"
DTB_skx2 = "${DEPLOY_DIR_IMAGE}/tegra186-tx2-cti-ASG916.dtb"

do_configure() {
    dtb_name=$(basename ${DTB} | cut -d '.' -f 1)
    sed -i -e "s/\[DTB_NAME\]/$dtb_name/g" ${WORKDIR}/flash.xml
    sed -i -e "s/\[DTB_NAME\]/$dtb_name/g" ${WORKDIR}/partition_specification.txt
}

do_compile() {
    tegrahost="${SHARED}/bootloader/tegrahost_v2"
    tegraparser="${SHARED}/bootloader/tegraparser_v2"
    tegrasign="${SHARED}/bootloader/tegrasign_v2"

    files=" \
        ${SHARED}/bootloader/mce_mts_d15_prod_cr.bin \ 
        ${SHARED}/bootloader/cboot.bin \
        ${SHARED}/bootloader/tos.img \
        ${SHARED}/bootloader/eks.img \
        ${SHARED}/bootloader/bpmp.bin \
        ${SHARED}/bootloader/camera-rtcpu-sce.bin \
        ${SHARED}/bootloader/t186ref/warmboot.bin \
        ${SHARED}/bootloader/t186ref/tegra186-a02-bpmp-quill-p3310-1000-c01-00-te770d-ucm2.dtb \
        ${DTB} \
        "

    for file in $files; do
        cp $file ${B}
    done

    cp ${WORKDIR}/flash.xml ${B}

    ${tegraparser} --pt flash.xml
    ${tegrahost} --chip 0x18 --partitionlayout flash.bin --list images_list.xml zerosbk
    ${tegrasign} --key None --list images_list.xml --pubkeyhash pub_key.key
    ${tegrahost} --chip 0x18 --partitionlayout flash.bin --updatesig images_list_signed.xml

    # Append validation bytes to our files
    cat ${B}/tos_sigheader.img.hash >> eks_sigheader.img.encrypt
    cat ${B}/eks_sigheader.img.hash >> bpmp_sigheader.img.encrypt
    cat ${B}/eks_sigheader.img.hash >> tegra186-a02-bpmp-quill-p3310-1000-c01-00-te770d-ucm2_sigheader.dtb.encrypt
    cat ${B}/eks_sigheader.img.hash >> camera-rtcpu-sce_sigheader.bin.encrypt
    # Warmboot magic
    dd if=/dev/zero bs=1 count=8 >> warmboot_wbheader.bin.encrypt
    cat ${B}/camera-rtcpu-sce_sigheader.bin.hash >> warmboot_wbheader.bin.encrypt
    dd if=/dev/zero bs=1 count=360 >> warmboot_wbheader.bin.encrypt
    dd if=${B}/camera-rtcpu-sce_sigheader.bin.encrypt bs=1 skip=384 count=48 >> warmboot_wbheader.bin.encrypt
}

do_deploy() {
    install -d ${DEPLOYDIR}/tegra-binaries-signed
    cp ${B}/*.encrypt ${DEPLOYDIR}/tegra-binaries-signed
    cp ${WORKDIR}/partition_specification.txt ${DEPLOYDIR}/tegra-binaries-signed
    cp ${DTB} ${DEPLOYDIR}
}

do_install() {
    install -d ${D}/opt/tegra-binaries-signed
    cp -r ${B}/*.encrypt ${D}/opt/tegra-binaries-signed
    cp ${WORKDIR}/partition_specification.txt ${D}/opt/tegra-binaries-signed
    cp ${DEPLOY_DIR_IMAGE}/u-boot-dtb.bin ${D}/opt/tegra-binaries-signed
    cp ${DTB} ${D}/opt/tegra-binaries-signed
}

FILES_${PN} += "/opt"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[depends] += "tegra-binaries:do_preconfigure"
do_compile[depends] += "virtual/kernel:do_deploy"
do_install[depends] += "virtual/kernel:do_deploy"
do_populate_lic[depends] += "tegra-binaries:do_unpack"

addtask do_deploy before do_package after do_install
