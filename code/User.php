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
	private $_password;
	/**
	 * @AttributeType ViRole
	 * /**
	 *  * @AssociationType ViRole
	 *  * @AssociationMultiplicity 0..1
	 *  * /
	 */
	public $_unnamed_ViRole_;
	/**
	 * @AttributeType ClassRole
	 * /**
	 *  * @AssociationType ClassRole
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_unnamed_ClassRole_;
	/**
	 * @AttributeType VideoRest
	 * /**
	 *  * @AssociationType VideoRest
	 *  * @AssociationMultiplicity 0..1
	 *  * /
	 */
	public $_unnamed_VideoRest_;
	/**
	 * @AttributeType ClassroomInventory
	 * /**
	 *  * @AssociationType ClassroomInventory
	 *  * /
	 */
	public $_unnamed_ClassroomInventory_;
	/**
	 * @AttributeType ClassroomRest
	 * /**
	 *  * @AssociationType ClassroomRest
	 *  * /
	 */
	public $_unnamed_ClassroomRest_;
}
?>