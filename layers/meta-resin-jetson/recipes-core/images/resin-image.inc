IMAGE_FSTYPES_append = " resinos-img"

RESIN_BOOT_PARTITION_FILES = " \
    ${KERNEL_IMAGETYPE}${KERNEL_INITRAMFS}-${MACHINE}.bin:/${KERNEL_IMAGETYPE} \
    "

# We chose this value so that the space resinOS takes will amount to 1GiB
IMAGE_ROOTFS_SIZE = "487424" 
RESIN_BOOT_SIZE = "80960"
                                                                                                                                                                            
PARTITION_TABLE_TYPE = "gpt"

IMAGE_INSTALL_append = " tegra-binaries-prepare"
