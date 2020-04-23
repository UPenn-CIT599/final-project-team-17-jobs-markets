package com.upenn.cit591.jobmarkets.libs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A generic helper class to read data from CSV file
 * @author zhongliu
 *
 */
public class CSVReader {

	public String csvFile;
	
	private HashMap<String, Integer> keysIndex = new HashMap<>();
	
	private ArrayList<HashMap> rowsData = new ArrayList<>();
	private int currentRow = -1;
	
	public CSVReader(String csvFile) {
		this.csvFile = csvFile;
		this.loadData();
		this.currentRow =-1;
	}
	

	/**
	 * Indicate whether there is another data row next.  
	 * @return false - already end of the file, no more row. true - yes, next row available. 
	 */
	public boolean hasNextRow() {
		return (this.currentRow+1)<this.rowsData.size()&&!this.isEmpty();
	}
	
	
	public boolean hasPreviousRow() {
		return (this.currentRow-1)>=0;
	}
	
	/**
	 * indicate whether data is available in CSV
	 * @return true or false
	 */
	public boolean isEmpty() {
		return this.rowsData.isEmpty();
	}
	
	/**
	 * move to next row
	 */
	public void moveToNextRow() {
		this.currentRow++;
	}
	
	/**
	 * return current data row, starting from 0
	 * @return
	 */
	public HashMap<String,String> currentRow(){
		if(this.currentRow>=0 && this.currentRow<this.rowsData.size()) {
			return this.rowsData.get(this.currentRow);
		}else {
			return null;
		}
	}
	
	/**
	 * return next data row 
	 * @return
	 */
	public HashMap<String,String> nextRow(){
		if(this.hasNextRow()) {
			this.currentRow++;
			return this.rowsData.get(this.currentRow);
		}else {
			return null;
		}
	}
	
	/**
	 * Get value of given column from give row data.
	 * @param columnName
	 * @param row one row's data
	 * @return alue of cell
	 */
	public String getCellValue(String columnName, HashMap<String,String> row) {
		columnName = columnName.toLowerCase().trim();
		if(this.keysIndex.get(columnName.toLowerCase().trim())==null) {
			System.err.println("Column:"+columnName+" doesn't exist");
			return null;
		}
		
		return row.get(columnName);
	}
	
	/**
	 * Get value of given column of current row. 
	 * @param columnName
	 * @return value of cell
	 */
	public String getCellValue(String columnName) {
		columnName = columnName.toLowerCase().trim();
		if(this.keysIndex.get(columnName)==null) {
			System.err.print("Column:"+columnName+" doesn't exist");
			return null;
		}
		
		return this.currentRow().get(columnName);
	}
	
	/**
	 * return previus data row 
	 * @return
	 */
	public HashMap<String,String> previousRow(){
		if(this.hasPreviousRow()) {
			this.currentRow--;
			return this.rowsData.get(this.currentRow);
		}else {
			return null;
		}
	}
	
	
	/**
	 * load data from a given CSV file. 
	 * @param csvFile
	 */
	private void loadData() {
		System.err.println("trying to load data from file:"+csvFile);
		File f = new File(csvFile);
		try {
			Scanner scanner = new Scanner(f);
			int row = 0;
			String keys[] = null;
			while(scanner.hasNextLine()) {
				String csvRow = scanner.nextLine();
				String values[] = null;
				if(row==0) {
					keys = csvRow.split(",");
					this.initLookupKeys(keys);
					System.out.println("key row="+csvRow);
					System.out.println("Row "+row+", keys size="+keys.length);
				}else {
					values = csvRow.split(",");
					System.out.println("Row "+row+", values size="+values.length);
					loadRowData(row, keys,values);
				}
				row++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't read file. Please make sure data file "+csvFile+" exists. ");
			e.printStackTrace();
		}
	}
	
	/**
	 * init columns as keys for lookup
	 * @param keys
	 */
	private void initLookupKeys(String[] keys) {
		for (int i=0;i<keys.length;i++) {
			String k = keys[i].toLowerCase().trim();
			keysIndex.put(k, Integer.valueOf(i));
//			System.out.println("Initializing key index, "+k+"="+i+"");
		}
	}
	
	/**
	 * load row data into HashMap
	 * @param row row number
	 * @param keys columns
	 * @param values 
	 */
	private void loadRowData(int row, String[] keys, String[] values) {
		if (this.keysIndex.isEmpty()) {
			this.initLookupKeys(keys);
		}
		
		if(values.length!=keys.length) {
			System.err.println("values.length!=keys.length for this row. "+row);
			return;
		}
		
		HashMap<String,String> rowData = new HashMap<>();
		for(int i=0;i<keys.length;i++) {
			rowData.put(keys[i].toLowerCase().trim(), values[i]);
		}
		
		this.rowsData.add(rowData);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
