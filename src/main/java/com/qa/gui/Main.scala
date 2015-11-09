package com.qa.gui

import com.qa.gui._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import com.qa.logic.EncryptPassword

/**
 * @author abutcher
 * 05/11/2015
 * An object that creates a frame that scenes are passed into
 */
object Main extends JFXApp {

   stage = new PrimaryStage()
  val LoginStage = new LoginStage(stage)

   
}