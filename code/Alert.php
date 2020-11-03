<?php
require_once(realpath(dirname(__FILE__)) . '/ReceivedVideoStream.php');

use ReceivedVideoStream;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Alert {
	/**
	 * @AttributeType String
	 */
	private $_message;
	/**
	 * @AttributeType int
	 */
	private $_code;
	/**
	 * @AttributeType ReceivedVideoStream
	 * /**
	 *  * @AssociationType ReceivedVideoStream
	 *  * /
	 */
	public $_ReceivedVideoStream_;
}
?>