SUMMARY = "Create flash artifacts without flashing"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${RESIN_COREBASE}/COPYING.Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = " \
    coreutils-native \
    virtual/bootloader \
    virtual/kernel \
    tegra-binaries \
    tegra-bootfiles \
    tegra186-flashtools-native \
    "

inherit deploy

SRC_URI = " \
    file://tegraflash-variables.sh \
    file://resinOS-flash.xml \
    file://partition_specification.txt \
    "

IMAGE_UBOOT ??= "u-boot-dtb"

KERNEL_DEVICETREE_orbitty-tx2 = "${DEPLOY_DIR_IMAGE}/tegra186-tx2-cti-ASG001-USB3.dtb"
KERNEL_DEVICETREE_n510-tx2 = "${DEPLOY_DIR_IMAGE}/tegra186-quill-p3310-1000-c03-00-base.dtb"
KERNEL_DEVICETREE_skx2 = "${DEPLOY_DIR_IMAGE}/tegra186-tx2-cti-ASG916.dtb"
KERNEL_DEVICETREE_spacely-tx2 = "${DEPLOY_DIR_IMAGE}/tegra186-tx2-cti-ASG006-IMX274-6CAM.dtb"
KERNEL_DEVICETREE_srd3-tx2 = "${DEPLOY_DIR_IMAGE}/d3-rsp-fpdlink-ov10640-single-j2.dtb"
KERNEL_DEVICETREE_j100-tx2 = "${DEPLOY_DIR_IMAGE}/tegra186-quill-p3310-1000-c03-00-j100.dtb"

DTBFILE ?= "${@os.path.basename(d.getVar('KERNEL_DEVICETREE', True).split()[0])}"

LDK_DIR = "${TMPDIR}/work-shared/L4T-${SOC_FAMILY}-${PV}-${PR}/Linux_for_Tegra"
B = "${WORKDIR}/build"
S = "${WORKDIR}"

BINARY_INSTALL_PATH = "/opt/tegra-binaries"

BOOTFILES=" \
    bmp.blob \
    bpmp.bin \
    camera-rtcpu-sce.bin \
    cboot.bin \
    eks.img \
    mb1_prod.bin \
    mb1_recovery_prod.bin \
    mce_mts_d15_prod_cr.bin \
    nvtboot_cpu.bin \
    nvtboot_recovery.bin \
    nvtboot_recovery_cpu.bin \
    preboot_d15_prod_cr.bin \
    slot_metadata.bin \
    spe.bin \
    tos.img \
    nvtboot.bin \
    warmboot.bin \
    minimal_scr.cfg \
    emmc.cfg \
    "

do_configure() {
    local f

    sed -i -e "s/\[DTB_NAME\]/$(echo ${DTBFILE} | cut -d '.' -f 1)/g" ${WORKDIR}/partition_specification.txt

    ln -fs "${STAGING_DATADIR}/tegraflash/${MACHINE}.cfg" -t "${B}"
    ln -fs "${DEPLOY_DIR_IMAGE}/${IMAGE_UBOOT}.bin" -t "${B}"
    ln -fs "${DEPLOY_DIR_IMAGE}/${DTBFILE}" -t "${B}"

    for f in ${BOOTFILES}; do
        ln -fs "${STAGING_DATADIR}/tegraflash/$f" -t "${B}"
    done
    for f in ${STAGING_DATADIR}/tegraflash/tegra186-*.cfg; do
        ln -fs $f -t "${B}"
    done
    for f in ${STAGING_DATADIR}/tegraflash/tegra186-a02-bpmp*.dtb; do
        ln -fs $f -t "${B}"
    done

    cat "${WORKDIR}/resinOS-flash.xml" | sed \
        -e"s,LNXFILE,${IMAGE_UBOOT}.bin," \
        -e"s,MB2TYPE,mb2_bootloader," -e"s,MB2FILE,nvtboot.bin," -e"s,MB2NAME,mb2," \
        -e"s,MPBTYPE,mts_preboot," -e"s,MPBFILE,preboot_d15_prod_cr.bin," -e"s,MPBNAME,mts-preboot," \
        -e"s,MBPTYPE,mts_bootpack," -e"s,MBPFILE,mce_mts_d15_prod_cr.bin," -e"s,MBPNAME,mts-bootpack," \
        -e"s,MB1TYPE,mb1_bootloader," -e"s,MB1FILE,mb1_prod.bin," -e"s,MB1NAME,mb1," \
        -e"s,BPFFILE,bpmp.bin," -e"s,BPFNAME,bpmp-fw," -e"s,BPFSIGN,true," \
        -e"s,BPFDTB-NAME,bpmp-fw-dtb," -e"s,BPMPDTB-SIGN,true," \
        -e"s,TBCFILE,cboot.bin," -e"s,TBCTYPE,bootloader," -e"s,TBCNAME,cpu-bootloader," \
        -e"s,TBCDTB-NAME,bootloader-dtb," -e"s,TBCDTB-FILE,${DTBFILE}," \
        -e"s,SCEFILE,camera-rtcpu-sce.bin," -e"s,SCENAME,sce-fw," -e"s,SCESIGN,true," \
        -e"s,SPEFILE,spe.bin," -e"s,SPENAME,spe-fw," -e"s,SPETYPE,spe_fw," \
        -e"s,WB0TYPE,WB0," -e"s,WB0FILE,warmboot.bin," -e"s,SC7NAME,sc7," \
        -e"s,TOSFILE,tos.img," -e"s,TOSNAME,secure-os," \
        -e"s,EKSFILE,eks.img," \
        -e"s,KERNELDTB-NAME,kernel-dtb," -e"s,KERNELDTB-FILE,${DTBFILE}," \
        > ${B}/flash.xml.in
}

boot0="boot0.img"
create_boot0_img() {
    # Create boot table

    # BCT
    cat ${1}/br_bct_BR.bct > ${1}/${boot0}
    cat ${1}/br_bct_BR.bct >> ${1}/${boot0}
    dd if=/dev/zero bs=16 count=576 >> ${1}/${boot0}
    cat ${1}/br_bct_BR.bct >> ${1}/${boot0}
    dd if=/dev/zero bs=16 count=800 >> ${1}/${boot0}

    # MB1
    cat ${1}/mb1_prod.bin.encrypt >> ${1}/${boot0}
    dd if=/dev/zero bs=16 count=10463 >> ${1}/${boot0}

    # MB1 BCT
    cat ${1}/mb1_cold_boot_bct_MB1_sigheader.bct.encrypt >> ${1}/${boot0}
    dd if=/dev/zero bs=16 count=967 >> ${1}/${boot0}

    # SPE
    cat ${1}/spe_sigheader.bin.encrypt >> ${1}/${boot0}
    dd if=/dev/zero bs=16 count=3071 >> ${1}/${boot0}

    # MB2
    cat ${1}/nvtboot_sigheader.bin.encrypt >> ${1}/${boot0}
    dd if=/dev/zero bs=16 count=10074 >> ${1}/${boot0}

    # MTS-PREBOOT
    cat ${1}/preboot_d15_prod_cr_sigheader.bin.encrypt >> ${1}/${boot0}

    # EXTRA
    dd if=/dev/zero bs=16 count=12445 >> ${1}/${boot0}
    dd if=${1}/slot_metadata.bin >> ${1}/${boot0}
    dd if=/dev/zero bs=1 count=10 >> ${1}/${boot0}
    dd if=/dev/zero bs=16 count=198654 >> ${1}/${boot0}
}

do_compile() {
    PATH="${STAGING_BINDIR_NATIVE}/tegra186-flash:${PATH}"

    cd ${B}
    . ${WORKDIR}/tegraflash-variables.sh
    tegraflash.py \
        --chip 0x18 --bl nvtboot_recovery_cpu.bin \
	      --sdram_config "${MACHINE}.cfg" \
	      --odmdata "${ODMDATA}" \
	      --applet mb1_recovery_prod.bin \
	      --cmd "flash" \
	      --cfg flash.xml \
	      --misc_config tegra186-mb1-bct-misc-si-l4t.cfg \
	      --pinmux_config "${PINMUX_CONFIG}" \
	      --pmic_config "${PMIC_CONFIG}" \
	      --pmc_config "${PMC_CONFIG}" \
	      --prod_config "${PROD_CONFIG}" \
	      --scr_config minimal_scr.cfg \
	      --br_cmd_config "${BOOTROM_CONFIG}" \
	      --dev_params emmc.cfg \
	      --bins "${BINS}" \
        --keep & \
        export _PID=$! ; wait ${_PID} || true
    cd -

    mkdir -p ${B}/out
    cp -r ${B}/${_PID}/* ${B}/out
    rm -rf ${B}/${_PID}

    create_boot0_img ${B}/out
}

do_install() {
    install -d ${D}/${BINARY_INSTALL_PATH}

    files=$(cat ${WORKDIR}/partition_specification.txt)

    for file in $files; do
        file_name=$(echo $file | cut -d ':' -f 2)
        cp ${B}/out/$file_name ${D}/${BINARY_INSTALL_PATH}
    done

    cp ${WORKDIR}/partition_specification.txt ${D}/${BINARY_INSTALL_PATH}
    cp ${B}/out/${boot0} ${D}/${BINARY_INSTALL_PATH}
}

do_deploy() {
    rm -rf ${DEPLOYDIR}/$(basename ${BINARY_INSTALL_PATH})
    mkdir -p ${DEPLOYDIR}/$(basename ${BINARY_INSTALL_PATH})
    cp -r ${D}/${BINARY_INSTALL_PATH}/* ${DEPLOYDIR}/$(basename ${BINARY_INSTALL_PATH})
}

FILES_${PN} += "${BINARY_INSTALL_PATH}"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[depends] += "tegra-binaries:do_preconfigure"
do_compile[depends] += "virtual/kernel:do_deploy"
do_install[depends] += "virtual/kernel:do_deploy"
do_populate_lic[depends] += "tegra-binaries:do_unpack"

addtask do_deploy before do_package after do_install
