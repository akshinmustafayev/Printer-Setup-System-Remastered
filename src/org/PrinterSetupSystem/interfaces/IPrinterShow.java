package org.PrinterSetupSystem.interfaces;

import org.PrinterSetupSystem.beans.Printer;
import org.PrinterSetupSystem.beans.PrinterType;

public interface IPrinterShow 
{
    public Printer GetPrinter(Integer printerid);
    public PrinterType GetPrinterType();
}