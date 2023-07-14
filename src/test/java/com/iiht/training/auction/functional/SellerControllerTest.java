package com.iiht.training.auction.functional;

import static com.iiht.training.auction.testutils.TestUtils.businessTestFile;
import static com.iiht.training.auction.testutils.TestUtils.currentTest;
import static com.iiht.training.auction.testutils.TestUtils.testReport;
import static com.iiht.training.auction.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.auction.controller.SellerController;
import com.iiht.training.auction.dto.BidsDto;
import com.iiht.training.auction.dto.SellerDto;
import com.iiht.training.auction.service.BidsService;
import com.iiht.training.auction.service.SellerService;
import com.iiht.training.auction.testutils.MasterData;

@WebMvcTest(SellerController.class)
@AutoConfigureMockMvc
public class SellerControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SellerService sellerService;
	@MockBean
	private BidsService bidsService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterSeller() throws Exception {
		SellerDto sellerDto = MasterData.getSellerDto();
		SellerDto savedSellerDto = MasterData.getSellerDto();
		savedSellerDto.setSellerId(1L);

		when(this.sellerService.registerSeller(sellerDto)).thenReturn(savedSellerDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sellers/register")
				.content(MasterData.asJsonString(sellerDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedSellerDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testRegisterSellerIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		SellerDto sellerDto = MasterData.getSellerDto();
		SellerDto savedSellerDto = MasterData.getSellerDto();
		savedSellerDto.setSellerId(1L);
		when(sellerService.registerSeller(sellerDto)).then(new Answer<SellerDto>() {

			@Override
			public SellerDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedSellerDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sellers/register")
				.content(MasterData.asJsonString(sellerDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllSellers() throws Exception {
		List<SellerDto> sellerDtos = MasterData.getSellerDtoList();

		when(this.sellerService.getAllSellers()).thenReturn(sellerDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sellers/get/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(sellerDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllSellersIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<SellerDto> sellerDtos = MasterData.getSellerDtoList();
		when(this.sellerService.getAllSellers()).then(new Answer<List<SellerDto>>() {

			@Override
			public List<SellerDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return sellerDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sellers/get/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetSellerById() throws Exception {
		SellerDto sellerDto = MasterData.getSellerDto();
		when(this.sellerService.getSellerById(1L)).thenReturn(sellerDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sellers/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(sellerDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetSellerByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		SellerDto sellerDto = MasterData.getSellerDto();
		sellerDto.setSellerId(1L);
		when(this.sellerService.getSellerById(1L)).then(new Answer<SellerDto>() {

			@Override
			public SellerDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return sellerDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sellers/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateSeller() throws Exception {
		SellerDto sellerDto = MasterData.getSellerDto();
		SellerDto savedSellerDto = MasterData.getSellerDto();
		savedSellerDto.setSellerId(1L);

		when(this.sellerService.updateSeller(sellerDto)).thenReturn(savedSellerDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/sellers/update")
				.content(MasterData.asJsonString(sellerDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedSellerDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateSellerIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		SellerDto sellerDto = MasterData.getSellerDto();
		SellerDto savedSellerDto = MasterData.getSellerDto();
		savedSellerDto.setSellerId(1L);
		when(sellerService.updateSeller(sellerDto)).then(new Answer<SellerDto>() {

			@Override
			public SellerDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedSellerDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/sellers/update")
				.content(MasterData.asJsonString(sellerDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteSeller() throws Exception {
		SellerDto sellerDto = MasterData.getSellerDto();
		when(this.sellerService.deleteSeller(1L)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/sellers/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteSellerIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		SellerDto sellerDto = MasterData.getSellerDto();
		sellerDto.setSellerId(1L);
		when(sellerService.deleteSeller(1L)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/sellers/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllBidsOnProduct() throws Exception {
		List<BidsDto> bidsDtos = MasterData.getBidsDtoList();

		when(this.bidsService.getAllBidsOnProduct(1L)).thenReturn(bidsDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sellers/get/bids-on-product/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bidsDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllBidsOnProductIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<BidsDto> bidsDtos = MasterData.getBidsDtoList();
		when(this.bidsService.getAllBidsOnProduct(1L)).then(new Answer<List<BidsDto>>() {

			@Override
			public List<BidsDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return bidsDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sellers/get/bids-on-product/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	

}
