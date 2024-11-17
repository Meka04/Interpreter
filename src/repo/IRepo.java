package repo;

import exceptions.RepoException;
import model.state.PrgState;

public interface IRepo {
    public PrgState getCrtPrg();
    public void add(PrgState p);
    void logPrgStateExec() throws RepoException;
    public void clear();
}
