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
	/**
	 * @AttributeType ClassroomInventory
	 * /**
	 *  * @AssociationType ClassroomInventory
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_ClassroomInventory_;
}
?>