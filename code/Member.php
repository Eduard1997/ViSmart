<?php
require_once(realpath(dirname(__FILE__)) . '/ClassRole.php');

use ClassRole;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Member extends ClassRole {

  public function join_Session($url_link, $meeting_id)
	{
		if (isset($_ClassRole_)) {
			$_VideoRooms = VideoRooms.getInstance();
			$_VideoRest_ = $_VideoRooms->find($url_link, $meeting_id);
			$user.setVRole(Participant());
	 }
	 else echo "Denied access";
 }
}
?>
