<?php
require_once(realpath(dirname(__FILE__)) . '/ViRole.php');
require_once(realpath(dirname(__FILE__)) . '/ClassRole.php');
require_once(realpath(dirname(__FILE__)) . '/VideoRest.php');
require_once(realpath(dirname(__FILE__)) . '/ClassroomInventory.php');
require_once(realpath(dirname(__FILE__)) . '/ClassroomRest.php');
require_once(realpath(dirname(__FILE__)) . '/Account.php');

use ViRole;
use ClassRole;
use VideoRest;
use ClassroomInventory;
use ClassroomRest;
use Account;

/**
 * @access public
 * @author mariaroxanaluca
 */
class User extends Account {
	public $_ViRole_;
	/**
	 * @AttributeType ClassRole
	 * /**
	 *  * @AssociationType ClassRole
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_ClassRole_;
	/**
	 * @AttributeType VideoRest
	 * /**
	 *  * @AssociationType VideoRest
	 *  * @AssociationMultiplicity 0..1
	 *  * /
	 */
	public $_VideoRest_;
	/**
	 * @AttributeType ClassroomInventory
	 * /**
	 *  * @AssociationType ClassroomInventory
	 *  * /
	 */
	public $_ClassroomInventory_;
	/**
	 * @AttributeType ClassroomRest
	 * /**
	 *  * @AssociationType ClassroomRest
	 *  * /
	 */
	public $_ClassroomRest_ = ClassroomRest.getInstance($this);

	public __constructor($class_role)
	{
		$_ClassRole_ = $class_role;
	}

	public function get_ClassRoomList()
	{
		if ($_ClassroomRest_-> hasUpdates())
			$_ClassroomInventory_ = $_ClassroomRest_-> getClassRoomList();
		return  $_ClassroomInventory_;
	}

	public function set_VRole($obj){
		if (!isset($_ViRole_))
			  $_ViRole_ = $obj;
	}

 public function sendMessage($given_message, $consignee){
	 // Then we send the message to the consignee
 }

 public function receiveMessage(){
	 // Then we send the message to the consignee
 }

}
?>
