inherit kernel-resin deploy

FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append = " \
    file://0001-Expose-spidev-to-the-userspace.patch \
    file://0002-mttcan-ivc-enable.patch \
    file://tegra186-tx2-cti-ASG001-USB3.dtb \
    file://tegra186-quill-p3310-1000-c03-00-base.dtb \
    file://tegra186-tx2-cti-ASG006-IMX274-6CAM.dtb \
    file://tegra186-tx2-cti-ASG916.dtb \
    file://d3-rsp-fpdlink-ov10640-single-j2.dtb \
    file://tegra186-tx2-blackboard.dtb \
    file://0002-NFLX-2019-001-SACK-Panic.patch \
    file://0003-NFLX-2019-001-SACK-Panic-for-lteq-4.14.patch \
    file://0004-NFLX-2019-001-SACK-Slowness.patch \
    file://0005-NFLX-2019-001-Resour-Consump-Low-MSS.patch \
    file://0006-NFLX-2019-001-Resour-Consump-Low-MSS.patch \
    "

SRC_URI_append_jetson-tx2 = " \
    file://0001-bcmdhd-Fix-wifi-disconnect-problems.patch \
"

SRC_URI_remove_skx2 = "  \
    file://0001-bcmdhd-Fix-wifi-disconnect-problems.patch \
"

SRC_URL_append_jetson-odmdata1-tx2 = " \
    file://0001-Add-gasket-module-1.1.3.patch \
"

RESIN_CONFIGS_append = " compat spi gamepad can tpg"
RESIN_CONFIGS_remove = "brcmfmac"

RESIN_CONFIGS[compat] = " \
    CONFIG_COMPAT=y \
    "

RESIN_CONFIGS[spi] = " \
		CONFIG_SPI=y \
		CONFIG_SPI_MASTER=y \
		CONFIG_SPI_SPIDEV=m \
		"
RESIN_CONFIGS_DEPS[spi] = " \
		CONFIG_QSPI_TEGRA186=y \
		CONFIG_SPI_TEGRA144=y \
		"

RESIN_CONFIGS[gamepad] = " \
		CONFIG_JOYSTICK_XPAD=m \
		"
RESIN_CONFIGS_DEPS[gamepad] = " \
		CONFIG_INPUT_JOYSTICK=y \
		CONFIG_USB_ARCH_HAS_HCD=y \
		"

RESIN_CONFIGS_append_skx2 = " cdc_acm wdm"

RESIN_CONFIGS_DEPS[cdc_acm] = "CONFIG_TTY=y"
RESIN_CONFIGS[cdc_acm] = "CONFIG_USB_ACM=m"

RESIN_CONFIGS[wdm] = "CONFIG_USB_WDM=m"

RESIN_CONFIGS_append_jetson-odmdata1-tx2 = " gasket"

RESIN_CONFIGS[gasket] = " \
        CONFIG_STAGING_GASKET_FRAMEWORK=m \
        CONFIG_STAGING_APEX_DRIVER=m \
        "

RESIN_CONFIGS[can] = " \
		CONFIG_CAN=m \
		CONFIG_CAN_RAW=m \
		CONFIG_CAN_DEV=m \
		CONFIG_MTTCAN=m \
		CONFIG_MTTCAN_IVC=m \
"

RESIN_CONFIGS_append_srd3-tx2 = " tpg"

RESIN_CONFIGS[tpg] = " \
		CONFIG_VIDEO_TEGRA_VI_TPG=m \
"

KERNEL_MODULE_AUTOLOAD_srd3-tx2 += "nvhost-vi-tpg"
KERNEL_MODULE_PROBECONF_srd3-tx2 += "nvhost-vi-tpg"

TEGRA_INITRAMFS_INITRD = "0"

KERNEL_ROOTSPEC = "\${resin_kernel_root} ro rootwait"
KERNEL_ROOTSPEC_FLASHER = "root=/dev/mmcblk1p2 ro rootwait"

KERNEL_ARGS_jetson-odmdata1-tx2 += "gasket.dma_bit_mask=32"

generate_extlinux_conf() {
    install -d ${D}/${KERNEL_IMAGEDEST}/extlinux
    rm -f ${D}/${KERNEL_IMAGEDEST}/extlinux/extlinux.conf
    rm -f ${D}/${KERNEL_IMAGEDEST}/extlinux/extlinux.conf_flasher
    kernelRootspec="${KERNEL_ROOTSPEC}" ; cat >${D}/${KERNEL_IMAGEDEST}/extlinux/extlinux.conf << EOF
DEFAULT primary
TIMEOUT 30
MENU TITLE Boot Options
LABEL primary
      MENU LABEL primary ${KERNEL_IMAGETYPE}
      LINUX /${KERNEL_IMAGETYPE}
      APPEND ${KERNEL_ARGS} ${kernelRootspec} \${os_cmdline}
EOF
    kernelRootspec="${KERNEL_ROOTSPEC_FLASHER}" ; cat >${D}/${KERNEL_IMAGEDEST}/extlinux/extlinux.conf_flasher << EOF
DEFAULT primary
TIMEOUT 30
MENU TITLE Boot Options
LABEL primary
      MENU LABEL primary ${KERNEL_IMAGETYPE}
      LINUX /${KERNEL_IMAGETYPE}
      APPEND ${KERNEL_ARGS} ${kernelRootspec} \${os_cmdline}
EOF
}

FILES_${KERNEL_PACKAGE_NAME}-image_append = "/boot/extlinux/extlinux.conf /boot/extlinux/extlinux.conf_flasher"

do_deploy_append() {
    mkdir -p "${DEPLOYDIR}"
    install -m 0600 "${D}/boot/extlinux/extlinux.conf" "${DEPLOYDIR}"
    install -m 0600 "${D}/boot/extlinux/extlinux.conf_flasher" "${DEPLOYDIR}"

    cp ${WORKDIR}/tegra186-tx2-cti-ASG001-USB3.dtb "${DEPLOYDIR}"
    cp ${WORKDIR}/tegra186-tx2-cti-ASG006-IMX274-6CAM.dtb "${DEPLOYDIR}"
    cp ${WORKDIR}/tegra186-tx2-cti-ASG916.dtb "${DEPLOYDIR}"
}

do_deploy_append_n510-tx2() {
    cp ${WORKDIR}/tegra186-quill-p3310-1000-c03-00-base.dtb "${DEPLOYDIR}"
}

do_deploy_append_srd3-tx2() {
    cp ${WORKDIR}/d3-rsp-fpdlink-ov10640-single-j2.dtb "${DEPLOYDIR}"
}

do_deploy_append_blackboard-tx2() {
    cp ${WORKDIR}/tegra186-tx2-blackboard.dtb "${DEPLOYDIR}"
}
