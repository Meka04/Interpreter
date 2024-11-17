package repo;

import exceptions.RepoException;
import model.state.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Repo implements IRepo{
    private List<PrgState> stateList;
    private int currentPrgStatePos;
    private String logFilePath;

    public Repo(String logFilePath){
        this.stateList = new ArrayList<>();
        this.currentPrgStatePos = -1;
        this.logFilePath = logFilePath;
    }
    @Override
    public void clear(){
        this.stateList.clear();
    }
    @Override
    public PrgState getCrtPrg(){
        if(currentPrgStatePos < 0 || currentPrgStatePos>=stateList.size())
            throw new RepoException("Invalid program state position");
        return this.stateList.get(this.currentPrgStatePos);
    }
    @Override
    public void add(PrgState p){
        stateList.add(p);
        this.currentPrgStatePos++;
    }
    @Override
    public void logPrgStateExec() throws RepoException{
        try{
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
            logFile.println(this.getCrtPrg());
            logFile.close();
        }
        catch(IOException e){
            throw new RepoException(e.getMessage());
        }
    }
}
