<?php
require_once(realpath(dirname(__FILE__)) . '/VideoStream.php');

use VideoStream;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Settings {
	/**
	 * @AttributeType bool
	 */
	private $allowScreenSharing;
	/**
	 * @AttributeType bool
	 */

	public function enableScreenSharing() {
		// Here the screen sharing sshould be enabled with a specific function
		$this->allowScreenSharing = True;
		echo 'The screen sharing has been enabled';
	}

	public function disableScreenSharing() {
	// Here the screen sharing should be disabled with a specific function
		$this->allowScreenSharing = True;
		echo 'The screen sharing has been disabled';
	}


	private $_allowVideoRecording;
	/**
	 * @AttributeType bool
	 */
	private $alerts = "enabled";
	/**
	 * @AttributeType VideoStream
	 * /**
	 *  * @AssociationType VideoStream
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public function enableAlerts(){
		if ($this->alerts == "disabled"){
			$this->alerts = "enabled";
		}
	}

	public function disableAlerts(){
		if ($this->alerts == "enabled"){
			$this->alerts = "disabled";
		}
	}

}
?>
