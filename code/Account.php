<?php
require_once(realpath(dirname(__FILE__)) . '/ScreenShare.php');
require_once(realpath(dirname(__FILE__)) . '/Video.php');
require_once(realpath(dirname(__FILE__)) . '/Message.php');

use ScreenShare;
use Video;
use Message;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Account {
	/**
	 * @AttributeType String
	 */
	private $_username;
	/**
	 * @AttributeType String
	 */
	private $_email;
	/**
	 * @AttributeType String
	 */
	private $_picture;
	/**
	 * @AttributeType ScreenShare
	 * /**
	 *  * @AssociationType ScreenShare
	 *  * /
	 */
	public $_ScreenShare_;
	/**
	 * @AttributeType Video
	 * /**
	 *  * @AssociationType Video
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_Video_;
	/**
	 * @AttributeType Message
	 * /**
	 *  * @AssociationType Message
	 *  * /
	 */
	public $_Message_;
}
?>