package org.ezcode.demo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.ezcode.demo.domain.ParseVO;
import org.ezcode.demo.domain.SearchDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ParseMapperTests
 */
@SpringBootTest
@Slf4j
public class ParseMapperTests {

    @Setter(onMethod_ = @Autowired)
    private ParseMapper parseMapper;


    @Test
    public void selectAllTest() {
        log.info("select test........................");

        SearchDTO dto = new SearchDTO();
		
        dto.setKeyword("별 찍기 반복문");
        dto.setLang("java");
        dto.setComment("c");
        dto.setAmount(3);
        
        log.info("" + parseMapper.selectAll(dto));
    }

    @Test
    public void insertTest() {
        log.info("insert test...........................");

        ParseVO vo = new ParseVO();

        String str = "C:\\ezcode\\Yekapark\\Test-master\\HellowWord.java";
        int idx = str.lastIndexOf("\\");
        String fname = str.substring(idx +1);
        String path = str.substring(0, idx);

        vo.setKeyword("별찍기");
        vo.setComment(1);
        vo.setCode("for(int i=0; i<5; i++) { log.info(22); }");
        vo.setLang("lang");
        vo.setPath(path);
        vo.setFname(fname);
        vo.setStart(32);

        log.info("" + parseMapper.insertCode(vo));
    }

    @Test
    public void selectByPnoTest() {
        log.info("select one test........................");

        log.info("" + parseMapper.selectByPno(13));
    }

    @Test
    public void selectTest() {

        SearchDTO dto = new SearchDTO();

        dto.setKeyword("별찍기 rttr");

        List<ParseVO> result = new ArrayList<>();

        dto.setComment("a");
        dto.setLang("java");

        Stream.of(dto.getKeywords()).forEach(r -> {
            dto.setKeyword(r);
            //log.info("" + dto);
            result.addAll(parseMapper.select(dto));
        });

        log.info("" + result);
        
    }

    @Test
    public void getCount() {
        SearchDTO dto = new SearchDTO();

        dto.setKeyword("fileName");
        dto.setComment("a");
        dto.setLang("java sql");

        log.info("카운트???????? " + parseMapper.getCount(dto));
    }
}