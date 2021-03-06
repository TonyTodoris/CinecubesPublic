package StoryMgr;
import java.util.ArrayList;

import AudioMgr.Audio;
import CubeMgr.CubeBase.CubeQuery;
import TaskMgr.SubTask;

public class PptxSlide extends Episode {
	
	
	public String Notes;
	public String Title;
	public String SubTitle;
	public String TitleColumn;
	public String TitleRow;
	public ArrayList<CubeQuery> CbQOfSlide;
	public long timeCreation;
	public long timeCreationAudio;
	public long timeCreationText;
	public long timeCreationTabular;
	public long timeCreationColorTable;
	public long timeCreationPutInPPTX;
	public long timeCombineSlide;
	public long timeComputeHighlights;
	
	public PptxSlide() {
		super();
		SubTitle="";
		Notes="";
		timeCreation=0;
		timeCreationAudio=0;
		timeCreationPutInPPTX=0;
		timeCreationTabular=0;
		timeCreationColorTable=0;
		timeCreationText=0;
		timeCreationPutInPPTX=0;
		timeCombineSlide=0;
		timeComputeHighlights=0;
		CbQOfSlide=new ArrayList<CubeQuery>();
	}
	
	/*public void setText(String txt){
		this.txt.setText(txt);
	}*/
	
	@Override
	public ArrayList<SubTask> getSubTasks() {
		return subTask;
	}

	@Override
	public void setSubTasks(ArrayList<SubTask> subtask) {
		subTask=subtask;
	}

	@Override
	public void addSubTask(SubTask subtask) {
		subTask.add(subtask);
	}
	
	@Override
	public void setVisual(Visual vis) {
		this.visual=vis;		
	}
	
	public Visual getVisual(){
		return visual;
	}

	public void setAudioFile(String fileName) {
		this.audio.setFileName(fileName);
	}
	
	public Audio getAudio(){
		return audio;
		
	}
	
}
