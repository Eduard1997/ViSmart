<?php
require_once(realpath(dirname(__FILE__)) . '/LiveChat.php');
require_once(realpath(dirname(__FILE__)) . '/User.php');
require_once(realpath(dirname(__FILE__)) . '/VideoStream.php');
require_once(realpath(dirname(__FILE__)) . '/Video.php');

use LiveChat;
use VideoStream;
use User;
use Video;

/**
 * @access public
 * @author mariaroxanaluca
 */
class VideoRest {
	/**
	 * @AttributeType String
	 */
	private $_meetingId;
	/**
	 * @AttributeType String
	 */
	private $_uRI;

	/**
	 * @AttributeType User
	 * /**
	 *  * @AssociationType User
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_VideoStream_;

	public $_Videos_ = array();
	/**
	 * @AttributeType Settings
	 * /**
	 *  * @AssociationType Settings
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */

	private $_ViCoordinator_;

	public __construct( $ViCoordinator){
		$_ViCoordinator_ = $ViCoordinator;
		$meeting_id = random_bytes(20);
		$url_link = "";
		self::$_VideoStream_ = VideoStream();
	}

	public function getState() {
		 return $this->$_VideoStream_;
	}

	public function setSettings($user,$settings)
	{
		if ($user == $_ViCoordinator_){
			$_VideoStream_ = VideoStream($settings);
			notifyAllObservers();
		}
	}

	public function attach($user){
		 $observer = Video($user);
		 $_Videos_.add($observer);
	}

	public function notifyAllObservers(){
		 foreach ( $observer as $_Videos_) {
				$observer.update();
		 }
	}

	public function checkCredits($url_link, $meeting_id){
		return ($_uRI === $url_link && $_meetingId===$meeting_id);
	}

}
?>
