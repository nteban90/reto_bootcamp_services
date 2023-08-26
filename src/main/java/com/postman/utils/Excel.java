package com.postman.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Excel {
    public static ArrayList<Map<String, String>> leerDatosDeHojaDeExcel(String rutaDeExcel, String hojaDeExcel)
            throws IOException {
        ArrayList<Map<String, String>> arrayListDatoPlanTrabajo = new ArrayList();
        Map<String, String> informacionProyecto = new HashMap();
        File file = new File(rutaDeExcel);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkbook.getSheet(hojaDeExcel);
        Iterator<Row> rowIterator = newSheet.iterator();
        Row titulos = rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.getColumnIndex();
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(),
                                cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(),
                                String.valueOf((long) cell.getNumericCellValue()));
                        break;
                    case BLANK:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), "");
                        break;
                    default:
                }
            }
            arrayListDatoPlanTrabajo.add(informacionProyecto);
            informacionProyecto = new HashMap();
        }
        return arrayListDatoPlanTrabajo;
    }

    public static void escrituraExcel(String ruta, String texto, int fila, int columna) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(ruta));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(fila);
        if (row == null) {
            row = sheet.createRow(fila);
        }
        Cell cell = row.getCell(columna);
        if (cell == null) {
            cell = row.createCell(columna);
        }
        cell.setCellValue(texto);
        try (FileOutputStream outputStream = new FileOutputStream(ruta)) {
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}