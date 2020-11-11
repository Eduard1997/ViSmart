<?php
require_once(realpath(dirname(__FILE__)) . '/ClassRole.php');

use ClassRole;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Module_Coordinator extends ClassRole {

  public function create_Session()
	{
		if (isset($_ClassRole_)) {
			$_VideoRooms = VideoRooms.getInstance();
      $_VideoRest_ = new VideoRest($this);
		  $_VideoRooms->add($_VideoRest_);
			$user.setVRole(ViCoordinator($_VideoRest_));
	 }
	 else echo "Denied access";
 }
}
?>
