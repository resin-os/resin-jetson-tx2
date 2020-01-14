deviceTypesCommon = require '@resin.io/device-types/common'
{ networkOptions, commonImg, instructions } = deviceTypesCommon

BOARD_POWEROFF = 'Remove and re-connect power to the board.'
BOARD_POWERON = 'Press and hold for 1 second the POWER push button.'

postProvisioningInstructions = [
	instructions.BOARD_SHUTDOWN
	instructions.REMOVE_INSTALL_MEDIA
	instructions.BOARD_REPOWER
]

module.exports =
	version: 1
	slug: 'jetson-odmdata1-tx2'
	aliases: [ 'jetson-odmdata1-tx2' ]
	name: 'Nvidia Jetson TX2 (ODMDATA Setting 1)'
	arch: 'aarch64'
	state: 'released'
	community: true

	stateInstructions:
		postProvisioning: postProvisioningInstructions

	instructions: [
		instructions.ETCHER_SD
		instructions.EJECT_SD
		instructions.FLASHER_WARNING
		BOARD_POWEROFF
		BOARD_POWERON
	].concat(postProvisioningInstructions)

	gettingStartedLink:
		windows: 'https://docs.resin.io/jetson-odmdata1-tx2/nodejs/getting-started/#adding-your-first-device'
		osx: 'https://docs.resin.io/jetson-odmdata1-tx2/nodejs/getting-started/#adding-your-first-device'
		linux: 'https://docs.resin.io/jetson-odmdata1-tx2/nodejs/getting-started/#adding-your-first-device'

	supportsBlink: false

	yocto:
		machine: 'jetson-odmdata1-tx2'
		image: 'resin-image-flasher'
		fstype: 'resinos-img'
		version: 'yocto-thud'
		deployArtifact: 'resin-image-flasher-jetson-odmdata1-tx2.resinos-img'
		compressed: true

	options: [ networkOptions.group ]

	configuration:
		config:
			partition:
				primary: 1
			path: '/config.json'

	initialization: commonImg.initialization
