DESCRIPTION = "Dummy recipe for bringing in CUDA command-line tools"
LICENSE = "MIT"

PR = "r1"

CUDA_COMPONENTS = "cuda-gdb cuda-nvprof cuda-memcheck cuda-nvdisasm cuda-cupti cuda-nvtx"
DEPENDS = "${CUDA_COMPONENTS}"

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

COMPATIBLE_MACHINE_class-target = "tegra"
PACKAGE_ARCH_class-target = "${TEGRA_PKGARCH}"

PACKAGES = "${PN} ${PN}-dev"
ALLOW_EMPTY_${PN} = "1"
RDEPENDS_${PN} = "${CUDA_COMPONENTS}"
BBCLASSEXTEND = "native nativesdk"
