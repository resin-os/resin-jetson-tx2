UBOOT_KCONFIG_SUPPORT = "1"

inherit resin-u-boot

RESIN_BOOT_PART = "0xC"
RESIN_DEFAULT_ROOT_PART = "0xD"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append = " \
  file://0001-Integrate-resin-u-boot.patch \
  file://0002-Change-default-root-partition.patch \
  "
