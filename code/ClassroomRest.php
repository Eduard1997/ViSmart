<?php
require_once(realpath(dirname(__FILE__)) . '/User.php');
require_once(realpath(dirname(__FILE__)) . '/ClassroomInventory.php');

use User;

/**
 * @access public
 * @author mariaroxanaluca
 */
class ClassroomRest {

			private final function  __construct() {
					echo __CLASS__ . " initializes only once\n";
			}

			public function getClassRoomsOnlyNames($user)
			{
				$_ClassroomInventory_ = ClassroomInventory();
				if (isset($user))
				{

				}
			}

			public function getChatChannel($user,$classroom){
				
			}
}
?>
