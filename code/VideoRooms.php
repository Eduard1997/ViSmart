<?php
require_once(realpath(dirname(__FILE__)) . '/VideoRest.php');

use VideoRest;

class VideoRooms {

   private $sessions = array();

   public function find($url_link, $meeting_id){
     foreach ($vr as $sessions){
       if ($sessions-> checkCredits($url_link, $meeting_id))
       {
         return $vr;
       }
     }
   }

}
