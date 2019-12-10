Change log
-----------

# v2.45.1+rev3
## (2019-12-09)

* resin-image-initramfs: Install tegra-xusb firmware [Alexandru Costache]

# v2.45.1+rev2
## (2019-12-04)

* bt-scripts: Solve BT related issues [Alexandru Costache]

# v2.45.1+rev1
## (2019-11-23)


<details>
<summary> Update meta-balena from v2.45.0 to v2.45.1 [Florin Sarbu] </summary>

> ## meta-balena-2.45.1
> ### (2019-11-21)
> 
> * Fix for a race condition where occasionally the supervisor might not be able to come up during boot. Also can be caused by using io.balena.features.balena-socket and app container restart always policy. Affects meta-balena 2.44.0 and 2.45.0. To be fixed in 2.44.1 and 2.46.0 [Zubair Lutfullah Kakakhel]
> * Rename resin to balena where possible [Pagan Gazzard]
> * Add leading new line for PACKAGE_INSTALL variable [Vicentiu Galanopulo]
> * Set `net.ipv4.ip_local_port_range` to recommended range (49152-65535) [Will Boyce]
> * No user impact, subtle fix in rollback version checks [Zubair Lutfullah Kakakhel]
</details>

# v2.45.0+rev2
## (2019-11-19)

* Update balena-yocto-scripts to v1.5.2 [Florin Sarbu]

# v2.45.0+rev1
## (2019-11-01)

* meta-tegra: Switch to thud 28.3.0 [Alexandru Costache]

<details>
<summary> Update meta-balena from v2.44.0 to v2.45.0 [Alexandru Costache] </summary>

> ## meta-balena-2.45.0
> ### (2019-10-30)
> 
> * Increase persistent journal size to 32M [Will Boyce]
> * Move persistent logs from state to data partition [Will Boyce]
> * Add wpa-supplicant recipe and update to v2.9 [Will Boyce]
> * Improve robustness by making variou services restart if they stop for some reason [Zubair Lutfullah Kakakhel]
> * Build net/dummy as module [Alexandru Costache]
</details>

# v2.44.0+rev1
## (2019-10-21)


<details>
<summary> Update meta-balena from v2.38.3 to v2.44.0 [Vicentiu Galanopulo] </summary>

> ## meta-balena-2.44.0
> ### (2019-10-03)
> 
> * Make uboot dev images autoboot delay build time configurable. Default is no delay [Zubair Lutfullah Kakakhel]
> * Reduce systemd logging level from info to notice [Zubair Lutfullah Kakakhel]
> * resin-supervisor: Expose container ID via env variable [Roman Mazur]
> * kernel-devsrc: Copy vdso.lds.S file in source archive if available [Sebastian Panceac]
> * Disable PasswordAuthentication in sshd in production images as an extra precautionary measure. [Zubair Lutfullah Kakakhel]
> * Update balena-engine to 18.9.10 [Robert Günzler]
> * hostapp-update-hooks: Filter out automount for inactive sysroot [Alexandru Costache]
> * Add support for hooks 2.0 enabling finer granularity during HostOS updates. [Zubair Lutfullah Kakakhel]
> * Update balena-supervisor to v10.3.7 [Cameron Diver]
> * Add support for balena cloud SSH public keys [Andrei Gherzan]
> * Map any user to root using libnss-ato [Andrei Gherzan]
> * Add option to disable kernel headers from being built. [Zubair Lutfullah Kakakhel]

> ## meta-balena-2.43.0
> ### (2019-09-13)
> 
> * Update NetworkManager to 1.20.2 [Andrei Gherzan]
> * Update ModemManager to 1.10.6 [Andrei Gherzan]

> ## meta-balena-2.42.0
> ### (2019-09-13)
> 
> * A small fix in initramfs when /dev/console is invalid due to whatever reason [Zubair Lutfullah Kakakhel]
> * Add automated testing for external kernel module header tarballs [Zubair Lutfullah Kakakhel]
> * Make sure correct utsrelease.h is packaged [Zubair Lutfullah Kakakhel]
> * Fix a bug where application containers with new systemd versions were failing to start in cases. Switch to systemd cgroup driver in balenaEngine [Zubair Lutfullah Kakakhel]

> ## meta-balena-2.41.1
> ### (2019-09-03)
> 
> * Update ModemManager to version 1.10.4 [Florin Sarbu]
> * Fix for some innocous systemd tmpfile warnings /var/run -> /run ones [Zubair Lutfullah Kakakhel]
> * Fix for rollbacks where the inactive partition mount was unavailable when altboot triggered [Zubair Lutfullah Kakakhel]
> * kernel-resin: Enable FTDI USB-serial convertors driver [Sebastian Panceac]

> ## meta-balena-2.41.0
> ### (2019-08-22)
> 
> * Fix a hang in initramfs for warrior production images [Zubair Lutfullah Kakakhel]
> * Update balena-engine to 18.09.8 [Robert Günzler]
> * Avoid overlayfs mounts in poky's volatile-binds [Andrei Gherzan]

> ## meta-balena-2.40.0
> ### (2019-08-14)
> 
> * Update balena-supervisor to v10.2.2 [Cameron Diver]
> * Workaround for a cornercase bug in PersistentLogging where journalctl filled the state partition. Vacuum the journal on boot. [Zubair Lutfullah Kakakhel]

> ## meta-balena-2.39.0
> ### (2019-07-31)
> 
> * usb-modeswitch-data: Switch Huawei E3372 12d1:1f01 to mbim mode [Alexandru Costache]
> * Fix rollback altboots to prevent good reboots by supervisor triggering rollback. [Zubair Lutfullah Kakakhel]
> * Devices using u-boot. Remove any BOOTDELAY for production images. Add a 2 seconds delay for development images [Zubair Lutfullah Kakakhel]
> * Devices using u-boot. Enable CONFIG_CMD_SETEXPR for all devices. Required for rollbacks to work [Zubair Lutfullah Kakakhel]
> * Devices using u-boot. Enable rollback-altboot by handling bootcount via meta-balena. [Zubair Lutfullah Kakakhel]
> * Production Devices using u-boot. Enable CONFIG_RESET_TO_RETRY to reset a device in case it drops into a u-boot shell [Zubair Lutfullah Kakakhel]
> * Remove confusing networkmanager https connectivity warning [Zubair Lutfullah Kakakhel]
> * Increase fs.inotify.max_user_instances to 512 [Zubair Lutfullah Kakakhel]
> * Update balena-supervisor to v10.0.3 [Cameron Diver]
> * Fix balena hello-world healthcheck [Zubair Lutfullah Kakakhel]
> * Add nf_table kernel modules [Zubair Lutfullah Kakakhel]
> * hostapp-update-hooks: Use correct source for inactive sysroot [Alexandru Costache]
> * Add extra healthcheck to balena service. It will spin up a hello-world container as well [Zubair Lutfullah Kakakhel]
> * Update balena-supervisor to v9.18.8 [Cameron Diver]
> * image-resin.bbclass: fixed a typo [Kyle Harding]
> * kernel-resin: Add support for CH340 family of usb-serial adapters [Sebastian Panceac]
> * resin-proxy-config: add missing reserved ip ranges to default noproxy [Will Boyce]
> * Reduce data partition size from 1G to 192M [Zubair Lutfullah Kakakhel]
</details>

# v2.38.3+rev5
## (2019-10-21)

* Update balena-yocto-scripts to v1.4.0 [Florin Sarbu]

# v2.38.3+rev4
## (2019-10-02)

* Update balena-yocto-scripts to v1.3.8 [Zubair Lutfullah Kakakhel]

# v2.38.3+rev3
## (2019-09-22)

* Update balena-yocto-scripts to v1.3.7 [Zubair Lutfullah Kakakhel]

# v2.38.3+rev2
## (2019-07-15)

* Update the balena-yocto-scripts submodule to v1.3.5 [Florin Sarbu]

# v2.38.3+rev1
## (2019-07-15)

* Update the balena-yocto-scripts submodule to v1.3.2 [Florin Sarbu]

<details>
<summary> Update meta-balena from v2.38.2 to v2.38.3 [Florin Sarbu] </summary>

> ## meta-balena-2.38.3
> ### (2019-07-10)
> 
> * resin-proxy-config: fix up incorrect bash subshell command [Matthew McGinn]
</details>

# v2.38.2+rev1
## (2019-07-04)


<details>
<summary> Update meta-balena from v2.38.1 to v2.38.2 [Florin Sarbu] </summary>

> ## meta-balena-2.38.2
> ### (2019-06-27)
> 
> * Update to kernel-modules-headers v0.0.20 to fix missing target modpost binary on kernel 5.0.3 [Florin Sarbu]
> * Update to kernel-modules-headers v0.0.19 to fix target objtool compile issue on kernel 5.0.3 [Florin Sarbu]
</details>

# v2.38.1+rev6
## (2019-07-03)

* Patches for TCP-based remote denial of service vulnerabilities [Vicentiu Galanopulo]

# v2.38.1+rev5
## (2019-07-03)

* Fix for "m_ttcan.o: file truncated" error [Vicentiu Galanopulo]

# v2.38.1+rev4
## (2019-06-25)

* Fix bluetooth not working on TX2 [Alexandru Costache]

# v2.38.1+rev3
## (2019-06-24)

* Update the balena-yocto-scripts submodule to v1.2.3 [Florin Sarbu]

# v2.38.1+rev2
## (2019-06-24)

* Update the balena-yocto-scripts submodule to v1.2.2 [Florin Sarbu]

# v2.38.1+rev1
## (2019-06-21)

* Update the balena-yocto-scripts submodule to v1.2.1 [Alexandru Costache]
* Update meta-balena from v2.37.0 to v2.38.1 [Alexandru Costache]

<details>
<summary> View details </summary>

## meta-balena-2.38.1
### (2019-06-20)

* Add warrior to compatible layers for meta-balena-common [Florin Sarbu]
* Fix image-resin.bbclass to be able to use deprecated layers [Andrei Gherzan]
* Fix kernel-devsrc on thud when kernel version < 4.10 [Andrei Gherzan]

## meta-balena-2.38.0
### (2019-06-14)

* Fix VERSION_ID os-release to be semver complient [Andrei Gherzan]
* Introduce META_BALENA_VERSION in os-release [Andrei Gherzan]
* Fix a case where changes to u-boot were not regenerating the config file at build time and using stale values. [Zubair Lutfullah Kakakhel]
* Use all.rp_filter=2 as the default value in balenaOS [Andrei Gherzan]
* Persist bluetooth storage data over reboots [Andrei Gherzan]
* Drop support for morty and krogoth Yocto versions [Andrei Gherzan]
* Add Yocto Warrior support [Zubair Lutfullah Kakakhel]
* Set both VERSION_ID and VERSION in os-release to host OS version [Andrei Gherzan]
* Bump balena-engine to 18.9.6 [Zubair Lutfullah Kakakhel]
* Downgrade balena-supervisor to v9.15.7 [Andrei Gherzan]
* Switch from dropbear to openSSH [Andrei Gherzan]
* Rename meta-resin-common to meta-balena-common [Andrei Gherzan]
* Add wifi firmware for rtl8192su [Zubair Lutfullah Kakakhel]
</details>

# v2.37.0+rev1
## (2019-06-07)

* Update meta-balena from v2.36.0 to v2.37.0 [Alexandru Costache]

<details>
<summary> View details </summary>

## meta-balena-2.37.0
### (2019-05-29)

* Update balena-supervisor to v9.15.8 [Cameron Diver]
* kernel-modules-headers: Update to v0.0.18 [Andrei Gherzan]
* os-config: Update to v1.1.1 to fix mDNS [Andrei Gherzan]
* Fix busybox nslookup mdns lookups [Andrei Gherzan]
* Update balena-supervisor to v9.15.4 [Cameron Diver]
* Improve logging and version comparison in linux-firmware cleanup class [Andrei Gherzan]
* Sync ModemManager recipe with upstream [Andrei Gherzan]
* Update NetworkManager to 1.18.0 [Andrei Gherzan]
</details>

* Update the balena-yocto-scripts submodule to v1.2.0 [Alexandru Costache]

# v2.36.0+rev3
## (2019-05-24)

* Add ODMDATA field for correct USB 3.0 port mapping [Sebastien Collier]

# v2.36.0+rev2
## (2019-05-21)

* Update the balena-yocto-scripts submodule to v1.1.1 [Florin Sarbu]

# v2.36.0+rev1
## (2019-05-21)

* Update meta-balena from v2.35.0 to v2.36.0 [Florin Sarbu]

<details>
<summary> View details </summary>

## meta-balena-2.36.0
### (2019-05-20)

* Cleanup old versions of iwlwifi firmware files in Yocto Thud [Florin Sarbu]
</details>

* Update the balena-yocto-scripts submodule to v1.1.0 [Florin Sarbu]

# v2.35.0+rev1
## (2019-05-19)

* Update the meta-balena submodule from v2.33.0 to v2.35.0 [Florin Sarbu]

<details>
<summary> View details </summary>

## meta-balena-2.35.0
### (2019-05-18)

* Update kernel-module-headers to version v0.0.16 [Florin Sarbu]
* Add uboot support for unified kernel cmdline arguments [Andrei Gherzan]
* Switch flasher detection in initramfs to flasher boot parameter [Andrei Gherzan]
* Update balena-supervisor to v9.15.0 [Cameron Diver]
* Improve boot speed by only mounting the inactive partition when needed [Zubair Lutfullah Kakakhel]
* Fix openssl dependency of balena-unique-key [Andrei Gherzan]
* Do not spawn getty in production images [Florin Sarbu]

## meta-balena-2.34.1
### (2019-05-14)

* Update balena-supervisor to v9.14.10 [Cameron Diver]

## meta-balena-2.34.0
### (2019-05-10)

* Add support to update a connectivity section in NetworkManager via config.json [Zubair Lutfullah Kakakhel]
* systemd: Fix journald configuration file [Andrei Gherzan]
* Add --max-download-attempts=10 to balenaEngine service to improve performance on shaky networks [Zubair Lutfullah Kakakhel]
* Update balena-engine to 18.09.5 [Zubair Lutfullah Kakakhel]
* Log initramfs messages to kernel dmesg to capture fsck, partition expand etc. command output [Zubair Lutfullah Kakakhel]
* kernel-resin: Add FAT fs specific configs to RESIN_CONFIGS [Sebastian Panceac]
* Update balena-supervisor to v9.14.9 [Cameron Diver]
* Introduce meta-balena yocto thud support [Andrei Gherzan]
* Update os-config to 1.1.0 [Andrei Gherzan]
</details>

# v2.33.0+rev7
## (2019-05-17)

* Add icon for blackboard-tx2 machine [Sebastien Collier]

# v2.33.0+rev6
## (2019-05-16)

* Add blackboard-tx2.conf machine [Sebastien Collier]
* Added custom dtb file for blackboard-tx2 [Sebastien Collier]
* Added custom blackboard-tx2 dtb to tegra186-flash-dry [Sebastien Collier]

# v2.33.0+rev5
## (2019-05-09)

* Compile video tpg as external module [Vicentiu Galanopulo]

# v2.33.0+rev4
## (2019-05-08)

* Add icon for srd3-tx2 [Florin Sarbu]

# v2.33.0+rev3
## (2019-05-07)

* Enable BT for Jetson TX2 based boards [Vicentiu Galanopulo]

# v2.33.0+rev2
## (2019-05-07)

* Add requested hypervisor.h, dma-iommu.h and camera_gpio.h to the kernel modules headers archive [Florin Sarbu]

# v2.33.0+rev1
## (2019-05-04)

* Update rust to 1.33 [Andrei Gherzan]
* Update meta-balena from v2.32.0 to v2.33.0 [Andrei Gherzan]

<details>
<summary> View details </summary>

## meta-balena-2.33.0
### (2019-05-02)

* Fixes for sysroot symlinks creation [Andrei Gherzan]
* libmbim: Refresh patches after last update to avoid build warnings [Andrei Gherzan]
* modemmanager: Refresh patches after last update to avoid build warnings [Andrei Gherzan]
* Make security flags inclusion yocto version specific [Andrei Gherzan]
* systemd: Make directory warning patch yocto version specific [Andrei Gherzan]
* Replace wireless tools by iw [Andrei Gherzan]
* systemd: Use a conf.d file for journald configuration [Andrei Gherzan]
* Set go verison to 1.10.8 to match balena-engine requirements [Andrei Gherzan]
* Update balena-engine to 18.09.3 [Andrei Gherzan]
* Update balena-supervisor to v9.14.6 [Cameron Diver]
* resin-u-boot: make devtool-compatible [Sven Schwermer]
* docker-disk: Disable unnecessary docker pid check [Armin Schlegel]
* Update libmbim to version 1.18.0 [Zahari Petkov]
* Update libqmi to version 1.22.2 [Zahari Petkov]
* Update to ModemManager v1.10.0 [Zahari Petkov]
* Add a OS_KERNEL_CMDLINE parameter that allows BSPs to easily add extra kernel cmdline args to production images [Zubair Lutfullah Kakakhel]
</details>

* Update the balena-yocto-scripts submodule to v1.0.6 [Florin Sarbu]

# v2.31.5+rev4
## (2019-05-03)

* Add CONFIG_VIDEO_TEGRA_VI_TPG as built-in [Vicentiu Galanopulo]
* Added custom srd3-tx2 dtb to tegra186-flash-dry [Vicentiu Galanopulo]
* Added ov10640 dtb for D3 [Vicentiu Galanopulo]
* Added srd3-tx2.conf machine [Vicentiu Galanopulo]

# v2.31.5+rev3
## (2019-04-29)

* CAN bus enablement [Vicentiu Galanopulo]

# v2.31.5+rev2
## (2019-04-24)

* Update the balena-yocto-scripts submodule to v1.0.5 [Florin Sarbu]
* Update repo.yml to be able to trigger VersionBot with `meta-balena` [Florin Sarbu]
* Rename meta-resin to meta-balena in repository [Florin Sarbu]
* Change the poky submodule to our github mirror [Florin Sarbu]

# v2.31.5+rev1
## (2019-03-22)

* Update the meta-resin submodule from v2.31.2 to v2.31.5 [Florin Sarbu]

<details>
<summary> View details </summary>

## meta-resin-2.31.5
### (2019-03-21)

* Update resin-supervisor to v9.11.3 [Andrei Gherzan]

## meta-resin-2.31.4
### (2019-03-20)

* resin-supervisor: Recreate on start if config has changed [Rich Bayliss]

## meta-resin-2.31.3
### (2019-03-20)

* Update resin-supervisor to v9.11.2 [Pablo Carranza Velez]
</details>

# v2.31.2+rev1
## (2019-03-20)

* Update the meta-resin submodule from v2.31.1 to v2.31.2 [Florin Sarbu]

<details>
<summary> View details </summary>

## meta-resin-2.31.2
### (2019-03-19)

* Update resin-supervisor to v9.11.1 [Pablo Carranza Velez]
</details>

# v2.31.1+rev1
## (2019-03-18)

* Update meta-resin from v2.31.0 to v2.31.1 [Pablo Carranza Velez]

# v2.31.0+rev2
## (2019-03-11)

* Mark n510-tx2 as community [Florin Sarbu]

# v2.31.0+rev1
## (2019-03-11)

* Update the meta-resin submodule from v2.29.2 to v2.31.0 [Florin Sarbu]

<details>
<summary> View details </summary>

## meta-resin-2.31.0
### (2019-03-08)

* README:md: Document dnsServers behaviour [Alexis Svinartchouk]
* Update resin-supervisor to v9.9.0 [Cameron Diver]
* Cleanup old versions of iwlwifi firmware files in Yocto sumo [Andrei Gherzan]
* Remove polkit dependency in balenaOS [Andrei Gherzan]
* Remove support for XFS file system [Andrei Gherzan]
* resin-init: update resin.io reference to balenaOS [Matthew McGinn]

## meta-resin-2.30.0
### (2019-02-28)

* resin-supervisor: Recreate on start if config has changed [Rich Bayliss]
* Generate the temporary kernel-devsrc compressed archive in WORKDIR instead of B [Florin Sarbu]
* balena-engine: Update to include fix for signal SIGRTMIN+3 [Andrei Gherzan]
* Reduce sleeps while trying to mount partition to speed up boot [Zubair Lutfullah Kakakhel]
* resin-expand: Reduce sleep duration to speed up boot [Zubair Lutfullah Kakakhel]
* initrdscripts: Reduce sleep to speed up boot [Zubair Lutfullah Kakakhel]
* Make balena-host daemon socket activated to reduce baseline cpu/memory usage [Zubair Lutfullah Kakakhel]
* Update resin-supervisor to v9.8.6 [Cameron Diver]
* Add support for aufs 4.18.11+, 4.19, 4.20 variants and update 4.14, 4.14.56+, 4.15, 4.16, 4.17, 4.18 [Florin Sarbu]
* balena-engine: Bump to include runc patch [Andrei Gherzan]
* Improve kernel-module-headers for v4.18+ kernels [Zubair Lutfullah Kakakhel]
* Update balena-supervisor to v9.8.3 [Cameron Diver]
* Ask chrony to quickly take measurements from custom NTP servers when they are added [Zubair Lutfullah Kakakhel]
* Disable in-tree rtl8192cu driver [Florin Sarbu]
* Prevent rollbacks from running if the previous OS is before v2.30.0 [Zubair Lutfullah Kakakhel]
* Change rollbacks to accept hex partition numbers for jetsons [Zubair Lutfullah Kakakhel]
* Convert partition numbers to hex in u-boot hook. Shouldn't affect any device. [Zubair Lutfullah Kakakhel]
* Reduce default reboot/poweroff timeouts from 30 minutes to 10 minutes [Zubair Lutfullah Kakakhel]
* Configure systemd tmpfiles to ignore supervisor tmp directories [Andrei Gherzan]
* Fixed "Can't have overlapping partitions." error in flasher [Alexandru Costache]
* Define default DNS servers behaviour with and without google DNS [Andrei Gherzan]
* Update balena-supervisor to v9.4.2 [Cameron Diver]
* Fix for some warnings [Zubair Lutfullah Kakakhel]
* Fix tini filename after balena-engine rename [Andrei Gherzan]
* Fix nm dispatcher hook when there are no custom ntp servers in config.json [Zubair Lutfullah Kakakhel]
* Improve persistent logging systemd service dependencies [Zubair Lutfullah Kakakhel]
* Update balena-supervisor to v9.3.0 [Cameron Diver]
* Use the new revision for balena source code [Florin Sarbu]
* Add a workaround for a bug where the chronyc online command in network manager hook would get stuck and eat cpu cycles [Zubair Lutfullah Kakakhel]
* Fix img to rootfs dependency when img is invalidated [Andrei Gherzan]
* Have boot partition type configurable as FAT32 [Andrei Gherzan]
* Deprecate morty and krogoth [Zubair Lutfullah Kakakhel]
* Deploy kernel source as a build artifact as well for external module compilation [Zubair Lutfullah Kakakhel]
* kernel-devsrc: Tarball up the kernel source and deploy it. [Zubair Lutfullah Kakakhel]
* kernel-modules-headers: Use the build directory for artifacts [Zubair Lutfullah Kakakhel]
* docs: Add documentation on nested changelogs [Giovanni Garufi]
* VersionBot: update upstream name and url [Giovanni Garufi]
</details>

* Update the balena-yocto-scripts submodule to v1.0.3 [Florin Sarbu]

# v2.29.2+rev4
## (2019-03-11)

* GSM Modems updates from kernel 4.14 [Vicentiu Galanopulo]

# v2.29.2+rev3
## (2019-02-18)

* Added touchscreen egalax kernel module [Vicentiu Galanopulo]

# v2.29.2+rev2
## (2019-02-05)

* Enable CONFIG_CMD_SETEXPR for u-boot [Alexandru Costache]

# v2.29.2+rev1
## (2019-01-28)

* Update the meta-resin submodule from v2.29.0 to v2.29.2 [Florin Sarbu]
* Update the balena-yocto-scripts submodule to v1.0.2 [Florin Sarbu]

# v2.29.0+rev3
## (2019-01-25)

* Upgrade TX2 from BETA to released status [Shaun Mulligan]

# v2.29.0+rev2
## (2018-12-23)

* Add patch with pixelformat descriptions [Niclas Moeslund Overby]

# v2.29.0+rev1
## (2018-12-20)

* Update meta-resin from v2.27.0 to v2.29.0 [Sebastian Panceac]

<details>
<summary> View details </summary>

## meta-resin-2.29.0
### (2018-12-19)

* OS will default apps.json to an empty json file [Andrei Gherzan]
* Update balena-engine to include low entropy fixes [Andrei Gherzan]
* Move an NM patch to the right place to reduce a warning [Zubair Lutfullah Kakakhel]
* Update balena-supervisor to v9.0.1 [Pablo Carranza Velez]
* Fix cases where RESIN_BOOT_PARTITION_FILES includes invalid entries [Andrei Gherzan]
* Enable some common linux kernel serial device drivers [Andrei Gherzan]
* Configure NetworkManager to only ignore our vpn interface but manage other tun devices [Andrei Gherzan]
* networkmanager: Add pppd to FILES [Zubair Lutfullah Kakakhel]
* networkmanager: Add balena-client-id.patch in bbappend [Zubair Lutfullah Kakakhel]
* Bump network manager from v1.12.2 to v1.14.4 [Zubair Lutfullah Kakakhel]
* Update balena-supervisor to v8.7.0 [Pablo Carranza Velez]
* Fix test cases for kernel module header compilation [Zubair Lutfullah Kakakhel]
* Add chrony v3.2 recipe in various layers to keep minimum chrony version on devices above v3.2 [Zubair Lutfullah Kakakhel]
* chrony/pyro: Add v3.2 recipe [Zubair Lutfullah Kakakhel]
* chrony/morty: Add v3.2 recipe [Zubair Lutfullah Kakakhel]
* chrony/krogoth: Bump recipe version to v3.2 [Zubair Lutfullah Kakakhel]
* Update resin-supervisor to v8.6.8 [Zubair Lutfullah Kakakhel]

## meta-resin-2.28.0
### (2018-12-05)

* Update os-config to 1.0.0 [Zahari Petkov]
* Update libqmi to version 1.20.2 [Florin Sarbu]
* Update libmbim to version 1.16.2 [Florin Sarbu]
* kernel-modules-headers: Add basic sanity test [Zubair Lutfullah Kakakhel]
* Fix kernel module header generation [Zubair Lutfullah Kakakhel]
* image-resin.bbclass: Fix config.json pretty format [Andrei Gherzan]
* Allow supervisor update on unmanaged devices [Andrei Gherzan]
* Update resin-supervisor to v8.6.3 [Cameron Diver]
</details>

# v2.27.0+rev3
## (2018-12-17)

* Switch from resin-yocto-scripts to balena-yocto-scripts [Florin Sarbu]

# v2.27.0+rev2
## (2018-12-13)

* Include extra NVidia utils in the rootfs [Florin Sarbu]

# v2.27.0+rev1
## (2018-11-26)

* Update the meta-resin submodule from v2.26.0 to v2.27.0 [Florin Sarbu]

<details>
<summary> View details </summary>

## meta-resin-2.27.0
### (2018-11-23)

* Expose randomMacAddressScan config.json knob [Andrei Gherzan]
* Move modemmanager udev rules in /lib/udev/rules.d [Andrei Gherzan]
* Fix modemmanager bbappend files [Andrei Gherzan]
* dnsmasq: Define 8.8.8.8 as a fallback nameserver [Andrei Gherzan]
* Increase timeout for filesystem label [Vicentiu Galanopulo]
* Add support for Huawei ME936 modem in MBIM mode [Florin Sarbu]
* Backport systemd sd-shutdown improvements for sumo versions [Florin Sarbu]
* Include avahi d-bus introspection files in rootfs [Andrei Gherzan]
* Fix custom udev rules when rule is removed from config.json [Zubair Lutfullah Kakakhel]
* resin-mounts: Add NetworkManager conf.d bind mounts [Zubair Lutfullah Kakakhel]
* Add support to pass custom configuration to NetworkManager [Zubair Lutfullah Kakakhel]
* README.md: Add info about SSH and Avahi services [Andrei Gherzan]
* Avoid xtables lock in resin-proxy-config [Andrei Gherzan]
* Migrate the supervisor to balena repositories [Florin Sarbu]
* Update balena-supervisor to v8.3.5 [Cameron Diver]
* Update supported modems list [Florin Sarbu]

## meta-resin-2.26.0
### (2018-11-05)

* Rename resin-unique-key to balena-unique-key [Andrei Gherzan]
* Don't let resin-unique-key rewrite config.json [Andrei Gherzan]
</details>

# v2.26.0+rev2
## (2018-11-26)

* Install n510-tx2 dtb only for this machine [Sebastian Panceac]
* Clarify contribution commit guidelines [Florin Sarbu]

# v2.26.0+rev1
## (2018-11-06)

* Update the meta-resin submodule from v2.25.0 to v2.26.0 [Florin Sarbu]

<details>
<summary> View details </summary>

## meta-resin-2.26.0
### (2018-11-05)

* Rename resin-unique-key to balena-unique-key [Andrei Gherzan]
* Don't let resin-unique-key rewrite config.json [Andrei Gherzan]

## meta-resin-2.25.0
### (2018-11-02)

* Generate ssh host key at first boot (not at first connection) [Andrei Gherzan]
* Fix extraneous space in kernel-resin.bbclass config [Florin Sarbu]
* Drop obsolete eval from kernel-resin's do_kernel_resin_reconfigure [Florin Sarbu]
* Add SyslogIdentifier for balena and resin-supervisor healthdog services [Matthew McGinn]
</details>

* Update the resin-yocto-scripts submodule to master HEAD [Florin Sarbu]

# v2.25.0+rev1
## (2018-11-05)

* Update the meta-resin submodule from v2.24.0 to v2.25.0 [Florin Sarbu]

<details>
<summary> View details </summary>

## meta-resin-2.25.0
### (2018-11-02)

* Generate ssh host key at first boot (not at first connection) [Andrei Gherzan]
* Fix extraneous space in kernel-resin.bbclass config [Florin Sarbu]
* Drop obsolete eval from kernel-resin's do_kernel_resin_reconfigure [Florin Sarbu]
* Add SyslogIdentifier for balena and resin-supervisor healthdog services [Matthew McGinn]

## meta-resin-2.24.1
### (2018-11-01)

* Update resin-supervisor to v8.0.0 [Pablo Carranza Velez]

## meta-resin-2.24.0
### (2018-10-24)

* resin-info: Small tweak for balenaCloud product [Andrei Gherzan]
* Update resin-supervisor to v7.25.8 [Pablo Carranza Velez]
* Rename resinOS to balenaOS [Andrei Gherzan]
</details>

* Add support for Aetina N510 carrier board [Raphael Dürscheid]

# v2.24.0+rev4:
## (2018-11-05)

* Add support for Aetina N510 carrier board [Raphael Dürscheid]

# v2.24.0+rev3
## (2018-11-02)

* Update contribution commit guidelines [nazrhom]

# v2.24.0+rev2
## (2018-10-25)

* Workaround for usb_modeswitch crash on 64 bits platforms [Florin Sarbu]

# v2.24.0+rev1
## (2018-10-24)

* Update the meta-resin submodule from v2.19.0 to v2.24.0 [Florin Sarbu]
* Pin skx2 to kernel version 4.4.38 [Florin Sarbu]

# v2.19.0+rev1
## (2018-09-25)

* Update the meta-resin submodule to v2.19.0 [Florin Sarbu]

<details>
<summary> View details </summary>

## meta-resin-2.19.0
### (2018-09-23)

* Update Balena to fix tty console hanging in some cases
* Pin down cargo deps (using Cargo.lock) to versions known working with rust 1.24.1 (for sumo)
* Remove duplicate packaging of bcm43143
* Set ModemManager to ignore Inca Roads Serial Device
* Add support for aufs 4.14.56+
* Update resin-supervisor to v7.19.7

## meta-resin-2.18.1
### (2018-09-14)

* Add a parsable representation of the changelog

## meta-resin-v2.18.0
### (2018-09-12)

* Update grub hooks to prepare to load kernel from root [Zubair Lutfullah Kakakhel]
* Update resin-supervisor to v7.19.4 [Cameron Diver]
* Kernel-resin.bbclass: Enable CONFIG_IP_NF_TARGET_LOG as a module [John (Jack) Brown]
* Balena: Update to current HEAD of 17.12-resin [Andrei Gherzan]
* Compress os-config with UPX on arm64 too [Andrei Gherzan]
* Update upx to 3.95 [Andrei Gherzan]
* Add support to skip flasher detection in env_resin.h [Zubair Lutfullah Kakakhel]
* Add the kernel to the rootfs [Zubair Lutfullah Kakakhel]
* Rework resin-supervisor systemd dependency on balena [Florin Sarbu]
* Enhanced security options for dropbear - sumo [Andrei Gherzan]
* Enhanced security options for dropbear - rocko [Andrei Gherzan]
* Enhanced security options for dropbear - pyro [Andrei Gherzan]
* Enhanced security options for dropbear - morty [Andrei Gherzan]
* Enhanced security options for dropbear - krogoth [Andrei Gherzan]

## meta-resin-2.17.0
### (2018-09-03)

* Resin-proxy-config: The no_proxy file fails to parse when missing EOL [Rich Bayliss]

</details>

* Add a parsable representation of the changelog [Giovanni Garufi]

# v2.17.0+rev1
## (2018-09-10)

* Update meta-resin submodule to v2.17.0 [Theodor Gherzan]

# v2.14.3+rev3
## (2018-09-07)

* Remove kernel-image package from rootfs [Florin Sarbu]

# v2.14.3+rev2
## (2018-09-06)

* Remove kernel-image package from machine [Zubair Lutfullah Kakakhel]
* Add versionist support [Giovanni Garufi]

# v2.14.3+rev1
## (2018-08-14)

* Update the meta-resin submodule to version v2.14.3 [Florin]

# v2.14.1+rev1
## (2018-08-10)

* Update the meta-resin submodule to version v2.14.1 [Florin]

# v2.14.0+rev3
## (2018-07-26)

* Clarify shutdown flashing instruction for the CTI Orbitty TX2 carrier board [Florin]

# v2.14.0+rev2
## (2018-07-23)

* Update the resin-yocto-scripts submodule to 8312741e13604a9d166370349061876afb22c0fa (on master branch) [Florin]

# v2.14.0+rev1
## (2018-07-18)

* Update the meta-resin submodule to version v2.14.0 [Florin]
* Update the resin-yocto-scripts submodule to 59ccd8558435ff6424827fb36ccb43b14650f4d4 (on master branch) [Florin]
* Add support for the CTI Spacely Carrier Board for Nvidia Jetson TX2 [Florin]
* Add support for the CTI Orbitty Carrier Board for Nvidia Jetson TX2 [Florin]

# v2.13.0+rev1
## (2018-06-12)

* Update the meta-resin submodule to version v2.13.0 [Theodor]
* Fix meta-tegra git history and update to current HEAD [Theodor]
* Include Xbox 360 gamepad driver kernel module [Laurence]

# v2.12.5+rev6
## (2018-04-09)

* Update the resin-yocto-scripts submodule to 6d6f7b29348323569f47c8acbf5963ff64d17647 (on master branch) [Florin]
* Fix boot partition number in the coffee file for skx2 [Florin]

# v2.12.5+rev5
## (2018-03-31)

* Various bug fixes introduced by recent refactoring [Theodor]

# v2.12.5+rev4
## (2018-03-31)

* Fix boot partition number [Theodor]

# v2.12.5+rev3
## (2018-03-30)

* Default root partition to the 12 [Theodor]
* Default enable of spi dev in userspace [Theodor]
* Upgrade to l4t 28.2.0 [Theodor]

# v2.12.5+rev2
## (2018-03-27)

* Update the resin-yocto-scripts submodule to 9cecb1ca4d9d4713dd337148b7d04a17afdba772 (on master branch) [Florin]

# v2.12.5+rev1
## (2018-03-22)

* Update the meta-resin submodule to version v2.12.5 [Florin]
* Update the resin-yocto-scripts submodule to 51b8849e2a02d0d4e729bff24909d9746e0bf4c3 (on master branch) [Florin]

# v2.12.3+rev1
## (2018-03-15)

* Update the meta-resin submodule to version v2.12.3 [Florin]
* Move to rocko [Theodor]

# v2.12.1+rev1
## (2018-03-12)

* Update the meta-resin submodule to version v2.12.1 [Andrei]

# v2.12.0+rev1
## (2018-03-08)

* Update the meta-resin submodule to version v2.12.0 [Theodor]

# v2.11.2+rev1
## (2018-03-08)

* Update the meta-resin submodule to version v2.11.2 [Andrei]

# v2.11.1+rev1
## (2018-03-08)

* Update the meta-resin submodule to version v2.11.1 [Andrei]

# v2.11.0+rev1
## (2018-03-08)

* Update the meta-resin submodule to version v2.11.0 [Theodor]

# v2.10.1+rev1
## (2018-03-01)

* Update the meta-resin submodule to version v2.10.1 [Florin]
* Remove obsolete submodule oe-meta-go [Florin]
* Update the resin-yocto-scripts submodule to dc9dfe466e48d934e55fb20a05156886873b1ab1 (on master branch) [Florin]
* Patch kernel to support Intel RealSense cameras [Theodor]

# v2.9.7+rev4
## 2018-02-21

* Rework the way we generate tegra binaries [Theodor]
* Generate boot0 image so we can correctly configure the partition table [Theodor]

# v2.9.7+rev3
## 2018-02-13

* Fix binary magic [Theodor]

# v2.9.7+rev2
## 2018-01-29

* Fix hostapps-update [Theodor]

# v2.9.7+rev1
## 2018-01-26

* Update rein-yocto-scripts to latest [Theodor]
* Update meta-resin submodule to v2.9.6 [Theodor]

# v2.8.1+rev2
## 2017-12-21

* Change display name Nvidia Jetson TX2 SK -> SKX2 [Theodor]
* Update meta-rust to pyro [Andrei]

# v2.8.1+rev1
## 2017-12-01

* Update meta-resin to include kernel header fix [Theodor]

# v2.7.4+rev2
## 2017-10-27

* Rename skx2 to Nvidia Jetson TX2 SK [Theodor]

# v2.7.4+rev1
## 2017-10-18

* Include cdc_amc and cdc_wdm module for the skx2 [Theodor]
* Various bug fixes [Theodor]

# v2.7.2+rev10 - 2017-10-18

* Fix root kernel parameter for resin-image-flasher [Theodor]
* Update meta-openembedded to latest pyro branch [Will]
* Update poky to latest pyro branch [Will]

# v2.7.2+rev9 - 2017-10-17

* Rename device-type to SKX2 [Theodor]

# v2.7.2+rev8 - 2017-10-17

* Fix name clash between device types [Theodor]

# v2.7.2+rev7 - 2017-10-16

* Add an icon to the SKX2 [Theodor]

# v2.7.2+rev6 - 2017-10-16

* Release SKX2 [Theodor]

# v2.7.2+rev5 - 2017-10-12

* Update resin-yocto-scripts to latest [Theodor]

# v2.7.2+rev4 - 2017-10-12

* Update resin-yocto-scripts to latest [Theodor]

# v2.7.2+rev3 - 2017-10-12

* Support new device type: SKX2 [Theodor]
* Integrate with resin u-boot [Theodor]

# v2.7.2+rev2 - 2017-10-05

* Fix boot partition index [Theodor]

# v2.7.2+rev1 - 2017-10-05

* Update meta-resin submodule to v2.7.2 [Andrei]
* Re-work Nvidia Jetson TX2 to update all internal partitions [Theodor]
* Include meta-rust layer [Will]
* Add meta-rust layer [Will]

# v2.3.0+rev1 - 2017-08-17

* Update the meta-resin submodule to version v2.3.0 [Florin]

# v2.2.0+rev1 - 2017-07-28

* Update the meta-resin submodule to version v2.2.0 [Florin]
* Update the resin-yocto-scripts submodule to HEAD of master [Florin]

# v2.1.0+rev2 - 2017-07-24

* Switch from aufs to overlayfs for the docker storage driver [Florin]

# v2.1.0+rev1 - 2017-07-21

* Update the meta-resin submodule to version v2.1.0 [Florin]
* Update the resin-yocto-scripts submodule to HEAD of master [Florin]
* Fix wireless chip  [Theodor]

# v2.0.8+rev1 - 2017-07-06

* Update the meta-resin submodule to version v2.0.8 [Florin]
* Update the resin-yocto-scripts submodule to HEAD of master [Florin]
* Provide initial support for the Nvidia Jetson TX2 [Theodor]
