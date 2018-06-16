package models;

import builders.BoardBuilder;
import com.gabrielhahn.gerencesy.model.Board;
import com.gabrielhahn.gerencesy.services.BoardService;
import com.gabrielhahn.gerencesy.utils.GenericDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class BoardTest {

    private BoardService service;

    @Before
    public void Setup() {
        service = new BoardService();
        GenericDao dao = Mockito.mock(GenericDao.class);
    }

    @Test
    public void getProcessoBoards() {
        List<Board> boards = service.getProcessoBoards(BoardBuilder.variosBoards().nowBoards());
        Assert.assertNotNull(boards);
    }

    @Test
    public void getTempoBoards() {
        List<Board> boards = service.getTempoBoards(BoardBuilder.variosBoards().nowBoards());
        Assert.assertNotNull(boards);
    }
}
