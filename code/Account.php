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
	private $username;
	/**
	 * @AttributeType String
	 */

	public function setUsername($param){
		self::$username = $param;
	}

	public function getUsername(){
		return $this->username;
	}

	private $email;
	/**
	 * @AttributeType String
	 */

	public function setEmail($param){
		self::$email = $param;
	}

	public function getEmail(){
		return $this->email;
	}


	private $picture;
	/**
	 * @AttributeType ScreenShare
	 * /**
	 *  * @AssociationType ScreenShare
	 *  * /
	 */

	public function setPicture($link_to_png){
		$picture = imagecreatefrompng($link_to_png);
	}

	public function getPicture(){
		return $this->picture;
	}


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
}
?>
