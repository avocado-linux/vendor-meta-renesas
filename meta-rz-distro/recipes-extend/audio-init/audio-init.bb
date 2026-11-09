FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI:append = " \
    file://audio-init.service \
    file://audio.sh \
    file://COPYING.MIT \
"
S = "${UNPACKDIR}"

inherit systemd

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/audio-init.service ${D}${systemd_system_unitdir}/audio-init.service

    install -d ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/audio.sh ${D}${bindir}/audio.sh
}

SYSTEMD_SERVICE:${PN} = "audio-init.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
