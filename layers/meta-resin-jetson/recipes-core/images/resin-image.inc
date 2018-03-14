IMAGE_FSTYPES_append = " resinos-img"

IMAGE_INSTALL_append = " tegra-binaries-prepare" 

RESIN_BOOT_PARTITION_FILES = " \
    ${KERNEL_IMAGETYPE}${KERNEL_INITRAMFS}-${MACHINE}.bin:/${KERNEL_IMAGETYPE} \
    "

# We chose this value so that the space resinOS takes will amount to 1GiB
IMAGE_ROOTFS_SIZE = "487424" 
RESIN_BOOT_SIZE = "80960"
                                                                                                                                                                            
PARTITION_TABLE_TYPE = "gpt"
DEVICE_SPECIFIC_SPACE = "49152"

do_image_resinos-img[depends] += " tegra-binaries-prepare:do_deploy" 

device_specific_configuration() {
    partitions=$(cat ${DEPLOY_DIR_IMAGE}/tegra-binaries/partition_specification.txt)

    START=${RESIN_IMAGE_ALIGNMENT}
    for n in ${partitions}; do
      part_name=$(echo $n | cut -d ':' -f 1)
      file_name=$(echo $n | cut -d ':' -f 2)
      END=$(expr ${START} \+ ${RESIN_IMAGE_ALIGNMENT})
      parted -s ${RESIN_RAW_IMG} unit KiB mkpart $part_name ${START} ${END}
      dd if=$(find ${DEPLOY_DIR_IMAGE}/tegra-binaries -name $file_name) of=${RESIN_RAW_IMG} conv=notrunc seek=1 bs=$(expr 1024 \* ${START})
      START=${END}
    done
}
