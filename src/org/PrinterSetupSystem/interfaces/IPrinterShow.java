package org.PrinterSetupSystem.interfaces;

import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.beans.PrinterType;

/** Represents IPrinterShow interface
@author Akshin A. Mustafayev
@version 1.0
*/
public interface IPrinterShow 
{
    public Printer GetPrinter(Integer printerid);
    public PrinterType GetPrinterType();
}