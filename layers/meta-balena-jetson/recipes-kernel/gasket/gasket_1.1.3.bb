SUMMARY = "Gasket kernel module for PCIe-based Coral Edge TPUs"
DESCRIPTION = "Gasket and Apex kernel modules from sources provided by Google for Coral Edge TPU devices."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

inherit module

SRC_URI = "file://apex_driver.c \
           file://apex.h \
           file://COPYING \
           file://gasket_constants.h \
           file://gasket_core.c \
           file://gasket_core.h \
           file://gasket_interrupt.c \
           file://gasket_interrupt.h \
           file://gasket_ioctl.c \
           file://gasket_ioctl.h \
           file://gasket_page_table.c \
           file://gasket_page_table.h \
           file://gasket_sysfs.c \
           file://gasket_sysfs.h \
           file://gasket.h \
           file://Kconfig \
           file://Makefile \
          "

S = "${WORKDIR}"

RPROVIDES_${PN} += "kernel-module-gasket kernel-module-apex"