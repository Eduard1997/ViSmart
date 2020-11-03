<?php
require_once(realpath(dirname(__FILE__)) . '/LiveChat.php');
require_once(realpath(dirname(__FILE__)) . '/User.php');
require_once(realpath(dirname(__FILE__)) . '/VideoStream.php');

use LiveChat;
use User;
use VideoStream;

/**
 * @access public
 * @author mariaroxanaluca
 */
class VideoRest {
	/**
	 * @AttributeType String
	 */
	private $_meetingId;
	/**
	 * @AttributeType String
	 */
	private $_uRI;
	/**
	 * @AttributeType LiveChat
	 * /**
	 *  * @AssociationType LiveChat
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_LiveChat_;
	/**
	 * @AttributeType User
	 * /**
	 *  * @AssociationType User
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_User_;
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