DESCRIPTION = "Boot-related tools for Tegra platforms"
HOMEPAGE = "https://github.com/OE4T/tegra-boot-tools"
LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f547d56278324f08919c3805e5fb8df9"

DEPENDS = "zlib systemd tegra-eeprom-tool"

SRC_URI = "https://github.com/OE4T/${BPN}/releases/download/v${PV}/${BP}.tar.gz"
SRC_URI[sha256sum] = "da67d7ab0b32b9e762b9bdceaba85fb00b14e9a228555073a03d772677d60c51"

OTABOOTDEV ??= "/dev/mmcblk0boot0"
OTAGPTDEV ??= "/dev/mmcblk0boot1"

EXTRA_OECONF = "--with-systemdsystemunitdir=${systemd_system_unitdir} \
                --with-machine-name=${MACHINE} \
                --with-bootdev=${OTABOOTDEV} --with-gptdev=${OTAGPTDEV}"

inherit autotools pkgconfig systemd

SYSTEMD_PACKAGES = "${PN}-earlyboot ${PN}-lateboot"
PACKAGES =+ "libtegra-boot-tools ${PN}-earlyboot ${PN}-lateboot ${PN}-updater"
SYSTEMD_SERVICE_${PN}-earlyboot = "bootcountcheck.service"
SYSTEMD_SERVICE_${PN}-lateboot = "update_bootinfo.service"
FILES_libtegra-boot-tools = "${libdir}/libtegra-boot-tools${SOLIBS} ${datadir}/tegra-boot-tools"
FILES_${PN}-earlyboot = "${sbindir}/bootcountcheck"
RDEPENDS_${PN}-earlyboot = "${PN}"
RDEPENDS_${PN}-lateboot = "${PN}"
FILES_${PN}-updater = "${bindir}/tegra-bootloader-update"
RDEPENDS_${PN}-updater = "${PN}"
FILES_${PN} += "${libdir}/tmpfiles.d"
