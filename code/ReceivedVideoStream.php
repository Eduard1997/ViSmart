<?php
require_once(realpath(dirname(__FILE__)) . '/Alert.php');
require_once(realpath(dirname(__FILE__)) . '/VideoStream.php');

use Alert;
use VideoStream;

/**
 * @access public
 * @author mariaroxanaluca
 */
class ReceivedVideoStream extends VideoStream {
	/**
	 * @AttributeType Alert
	 * /**
	 *  * @AssociationType Alert
	 *  * /
	 */
	public $_Alert_;
}
?>