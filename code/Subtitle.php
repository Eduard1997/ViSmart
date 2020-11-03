<?php
require_once(realpath(dirname(__FILE__)) . '/ReceivedVideoStream.php');

use ReceivedVideoStream;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Subtitle {
	/**
	 * @AttributeType String
	 */
	private $_text;
	/**
	 * @AttributeType ReceivedVideoStream
	 * /**
	 *  * @AssociationType ReceivedVideoStream
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_ReceivedVideoStream_;
}
?>