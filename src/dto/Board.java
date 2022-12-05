package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private int bhitcount;
	private String bfileName;
	private String bsavedName;
	private String bfileType;
}
