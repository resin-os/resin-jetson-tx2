#
#  Makefile for Gasket framework and dependent drivers.
#

obj-$(CONFIG_STAGING_GASKET_FRAMEWORK)	+= gasket.o
obj-$(CONFIG_STAGING_APEX_DRIVER)	+= apex.o

gasket-objs	:= gasket_core.o gasket_ioctl.o gasket_interrupt.o gasket_page_table.o gasket_sysfs.o
apex-objs	:= apex_driver.o

SRC := $(shell pwd)

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC)

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) clean
