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
	private $_allowScreenSharing;
	/**
	 * @AttributeType bool
	 */
	private $_allowVideoRecording;
	/**
	 * @AttributeType bool
	 */
	private $_allowAlerts;
	/**
	 * @AttributeType VideoStream
	 * /**
	 *  * @AssociationType VideoStream
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_VideoStream_;
}
?>