# The SKX2 has a TOBY-L210 modem, and recently
# the u-blox modeswitch scripts were moved from
# meta-raspberrypi to meta-balena. However,
# the u-blox-switch.sh script triggers a
# reboot in the case of succesfull mode switch,
# which could impact HUP if it hppened right after
# booting the new OS. Also, it is reported that
# the modeswitch script is not very stable and fails
# frequently, so it's better if we did not include
# this change for the SKX until this gets fixed.
# See: https://github.com/balena-os/meta-balena/issues/2039
# and https://github.com/balena-os/balena-raspberrypi/pull/510

SRC_URI_remove = " \
    file://77-mm-u-blox-modeswitch.rules \
    file://u-blox-switch@.service \
    file://u-blox-switch.sh \
"

do_install_remove = "install -m 0644 ${WORKDIR}/77-mm-u-blox-modeswitch.rules ${D}${base_libdir}/udev/rules.d"
do_install_remove = "install -m 0755 ${WORKDIR}/u-blox-switch.sh ${D}${bindir}"
do_install_remove = "install -m 0644 ${WORKDIR}/u-blox-switch@.service ${D}${systemd_unitdir}/system"

FILES_${PN}_remove = "${base_libdir}/udev/rules.d/77-mm-u-blox-modeswitch.rules"
FILES_${PN}_remove = "${systemd_unitdir}/system/u-blox-switch@.service"
FILES_${PN}_remove = "${bindir}/u-blox-switch.sh"
