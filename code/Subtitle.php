<?php
require_once(realpath(dirname(__FILE__)) . '/ReceivedVideoStream.php');

use ReceivedVideoStream;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Subtitle {
	/**
	 * @AttributeType String
	 */
	private $subtitles;
	/**
	 * @AttributeType ReceivedVideoStream
	 * /**
	 *  * @AssociationType ReceivedVideoStream
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */

	public function enableSubtitles(){
		if ($this->subtitles == "disabled"){
			$this->subtitles = "enabled";
	}

	public function disableSubtitles(){
		if ($this->subtitles == "enabled"){
			$this->subtitles = "disabled";
	}

	public function getSubtitlesStatus(){
		return $this->subtitles;
	}

	public function showSubtitles(){
		if (getSubtitlesStatus() == "enabled"){
			//Run script/function that displays subtitles on video
		}
	}

	public $ReceivedVideoStream;

	public function addSubtitlesToVideo($ReceivedVideoStream){
		// Add/process subtitles
	}


}
?>