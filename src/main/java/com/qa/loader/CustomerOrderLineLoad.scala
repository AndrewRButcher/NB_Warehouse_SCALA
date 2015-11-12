package com.qa.loader

import com.qa.dbConnector.SQL
import com.qa.Entities.CustomerOrderLine
import java.sql.ResultSet
import scalafx.collections.ObservableBuffer

/**
 * @author abutcher
 */
class CustomerOrderLineLoad {
    var connector: SQL = new SQL
  var customerOrderLineList: ObservableBuffer[CustomerOrderLine] = new ObservableBuffer[CustomerOrderLine]
  
    /**
   * Function to iterate through a result set and create customer order line entities
   * @param sql String to be executed
   */
  def constructResults(sql: String) = {

    customerOrderLineList clear ()
    try {
      connector openSQLCon
      val rs: ResultSet = connector querySQLDB (sql)
      def scanResultSet: Unit = {
        if (rs.next) {
          val cOID = rs getInt("idCustomerOrder")
          val itemID = rs getInt("idItem")
          val itemName = getItemNameByItemID(itemID)
          val quantity = rs getInt("quantity")
          val cOL = new CustomerOrderLine(cOID, itemID, itemName, quantity)
          customerOrderLineList += cOL
          scanResultSet
        }
      }
      scanResultSet
      rs.close()
    } catch {
      case e: Exception => println("Error Executing Query")
    } finally {
      connector closeSQLCon
    }
  }

  def getPurchaseOrderLinesByPurchaseOrderID(id: Int): ObservableBuffer[CustomerOrderLine] = {
    val sql: String = "SELECT * FROM nbgardensdata.customerorderline WHERE idCustomerOrder =" + id + ";"
    constructResults(sql)
    customerOrderLineList
  }
  
  def getItemNameByItemID(id:Int):String = {
        val sql: String = "SELECT * FROM nbgardensdata.item WHERE itemID =" + id + ";"
    var iN: String = null
    try {
      connector openSQLCon
      val rs: ResultSet = connector querySQLDB (sql)
      def scanResultSet: Unit = {
        if (rs.next) {
          iN = rs getString ("itemName")
        }
      }
      scanResultSet
      rs.close
    } catch {
      case e: Exception => println("Error Executing Name Query")
    } finally {
      connector closeSQLCon
    }
    iN
  }
}