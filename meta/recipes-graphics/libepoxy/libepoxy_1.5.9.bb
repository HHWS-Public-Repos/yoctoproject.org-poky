SUMMARY = "OpenGL function pointer management library"
DESCRIPTION = "It hides the complexity of dlopen(), dlsym(), \
glXGetProcAddress(), eglGetProcAddress(), etc. from the app developer, with \
very little knowledge needed on their part. They get to read GL specs and \
write code using undecorated function names like glCompileShader()."
HOMEPAGE = "https://github.com/anholt/libepoxy/"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=58ef4c80d401e07bd9ee8b6b58cf464b"

SRC_URI = "${GITHUB_BASE_URI}/download/${PV}/${BP}.tar.xz \
           file://0001-dispatch_common.h-define-also-EGL_NO_X11.patch \
           "
SRC_URI[sha256sum] = "d168a19a6edfdd9977fef1308ccf516079856a4275cf876de688fb7927e365e4"
GITHUB_BASE_URI = "https://github.com/anholt/libepoxy/releases"

inherit meson pkgconfig features_check github-releases

REQUIRED_DISTRO_FEATURES = "opengl"

PACKAGECONFIG[egl] = "-Degl=yes, -Degl=no, virtual/egl"
PACKAGECONFIG[x11] = "-Dglx=yes, -Dglx=no -Dx11=false, virtual/libx11 virtual/libgl"
PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} egl"

EXTRA_OEMESON += "-Dtests=false"

PACKAGECONFIG:class-native = "egl x11"
PACKAGECONFIG:class-nativesdk = "egl x11"

BBCLASSEXTEND = "native nativesdk"

