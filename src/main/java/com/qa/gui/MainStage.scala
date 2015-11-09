package com.qa.gui

import com.qa.gui._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.GridPane
import javafx.geometry.Insets
import scalafx.scene.text._
import scalafx.scene.control._
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color

/**
 * @author abutcher
 */
class MainStage(stage: PrimaryStage) {  
  val titleS:String = "NB Gardens - Warehouse Order Tracking System - "
  stage.setTitle(titleS+ "Purchase Orders")
  val label: Label = new Label("Purchase Orders")
  label.setFont(Font.font ("Verdana", 30))
  val border: BorderPane = new BorderPane()
  val poB: Button = new Button("Purchase Orders")
  val coB: Button = new Button("Customer Orders")
  val logOB:Button = new Button("Logout")

  val sideBar:VBox = new VBox
    sideBar.setPadding(new Insets(10));
    sideBar.setSpacing(8);
  
  poB.onAction = { ae: ActionEvent => 
    stage.setTitle(titleS + "Purchase Orders")
    label.setText("Purchase Orders")
    border.center = new PurchaseOrderStage(stage).createPane }
  coB.onAction = { ae: ActionEvent => 
    stage.setTitle(titleS + "Customer Orders")
    label.setText("Customer Orders")
    border.center = new CustomerOrderStage(stage).createPane }
  
  logOB.onAction={ae: ActionEvent => new LoginStage(stage)}
  
  sideBar.children add(poB)
  sideBar.children add(coB)
  sideBar.children add(logOB)
  
  def createScene: Scene = {

    var scene: Scene = new Scene {
      border setTop (label)
      border setCenter (new PurchaseOrderStage(stage).createPane)
      content addAll(border)
      border setLeft(sideBar)
    }
    scene
  }

  stage.setScene(createScene)
}