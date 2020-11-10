<?php
require_once(realpath(dirname(__FILE__)) . '/Account.php');
require_once(realpath(dirname(__FILE__)) . '/VideoStream.php');

use Account;
use VideoStream;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Video {
	private $_volume;
	private $_image;
	/**
	 * @AttributeType Account
	 * /**
	 *  * @AssociationType Account
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_Account_;
	/**
	 * @AttributeType VideoRest
	 * /**
	 *  * @AssociationType VideoRest
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_VideoRest_;

	function __construct Video($_VideoRest_){
		$_VideoRest_ = $_VideoRest_;
		$_VideoRest_.attach($this);
	}

	public function update(){
			
	}
}
?>
