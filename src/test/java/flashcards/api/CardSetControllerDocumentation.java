package flashcards.api;

import static org.mockito.Matchers.any;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import flashcards.domain.CardSet;
import flashcards.dto.CardSetDto;
import flashcards.mapper.CardSetMapper;
import flashcards.mapper.CardSetMapperImpl;
import flashcards.repository.CardSetRepository;
import flashcards.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class CardSetControllerDocumentation {

    @InjectMocks
    private CardSetController cardSetController;

    @Mock
    private CardSetRepository cardSetRepository;
    @Spy
    private CardSetMapper cardSetMapper = new CardSetMapperImpl();
    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    private RestDocumentationResultHandler document;

    @Before
    public void setUp() {
        this.document = MockMvcRestDocumentation.document("{method-name}/",
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));
        mockMvc = MockMvcBuilders.standaloneSetup(cardSetController)
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation))
                .build();

        Mockito.when(cardSetRepository.findByUserInfo(any(), any())).thenReturn(createCardSets());
    }

    @Test
    public void cardSet() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/card-set")
                .param("page", "0")
                .param("size", "10");
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(this.document);
    }

    private List<CardSet> createCardSets() {
        return Arrays.asList(
                new CardSet()
                        .setId(10L)
                        .setTitle("title")
        );
    }

}