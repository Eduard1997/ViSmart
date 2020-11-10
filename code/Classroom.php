<?php
require_once(realpath(dirname(__FILE__)) . '/Chanels.php');
require_once(realpath(dirname(__FILE__)) . '/ClassroomInventory.php');

use Chanels;
use ClassroomInventory;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Classroom {
	/**
	 * @AttributeType Chanels
	 * /**
	 *  * @AssociationType Chanels
	 *  * @AssociationKind Aggregation
	 *  * /
	 */
	public $_Chanels_;

	public $_name_;

	public function setName($param){
		self::$_name_ = $param;
	}

	public function getName(){
		return $this->$_name_;
	}
}
?>
