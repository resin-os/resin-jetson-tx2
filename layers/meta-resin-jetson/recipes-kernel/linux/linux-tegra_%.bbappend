inherit kernel-resin deploy

FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append = " file://tegra186-tx2-cti-ASG916.dtb \
                   file://tegra186-tx2-cti-ASG001-USB3.dtb \
                   "

RESIN_CONFIGS_append = " compat"

RESIN_CONFIGS[compat] = " \
    CONFIG_COMPAT=y \
    "
RESIN_CONFIGS_remove = "brcmfmac"

RESIN_CONFIGS_append_skx2 = " cdc_acm wdm"
RESIN_CONFIGS_append_cti-orbitty-tx2 = " cdc_acm wdm"

RESIN_CONFIGS_DEPS[cdc_acm] = "CONFIG_TTY=y"
RESIN_CONFIGS[cdc_acm] = "CONFIG_USB_ACM=m"

RESIN_CONFIGS[wdm] = "CONFIG_USB_WDM=m"

TEGRA_INITRAMFS_INITRD = "0"

KERNEL_ROOTSPEC = "\${resin_kernel_root} ro rootwait"
KERNEL_ROOTSPEC_FLASHER = "root=/dev/mmcblk1p13 ro rootwait"

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
      APPEND ${KERNEL_ARGS} ${kernelRootspec}
EOF
    kernelRootspec="${KERNEL_ROOTSPEC_FLASHER}" ; cat >${D}/${KERNEL_IMAGEDEST}/extlinux/extlinux.conf_flasher << EOF
DEFAULT primary
TIMEOUT 30
MENU TITLE Boot Options
LABEL primary
      MENU LABEL primary ${KERNEL_IMAGETYPE}
      LINUX /${KERNEL_IMAGETYPE}
      APPEND ${KERNEL_ARGS} ${kernelRootspec}
EOF
}

do_deploy_append() {
    mkdir -p "${DEPLOYDIR}"
    install -m 0600 "${D}/boot/extlinux/extlinux.conf" "${DEPLOYDIR}"
    install -m 0600 "${D}/boot/extlinux/extlinux.conf_flasher" "${DEPLOYDIR}"

    cp ${WORKDIR}/tegra186-tx2-cti-ASG916.dtb "${DEPLOYDIR}"
    cp ${WORKDIR}/tegra186-tx2-cti-ASG001-USB3.dtb "${DEPLOYDIR}"
}
