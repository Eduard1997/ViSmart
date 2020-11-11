<?php
require_once(realpath(dirname(__FILE__)) . '/ViRole.php');

use ViRole;

/**
 * @access public
 * @author mariaroxanaluca
 */
class ViCoordinator extends ViRole {

   private $_VideoRest_;

  public function __constructor($VideoRest)
  {
    $_VideoRest_ = $VideoRest;
  }

  public function updateSettings($settings)
  {
    $_VideoRest_.setSettings($this, $settings);
  }
}
?>
