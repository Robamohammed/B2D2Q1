using AventStack.ExtentReports;
using BestBuyApp.Model;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using RestSharp;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Text;

namespace BestBuyTest.Test
{
    [TestClass]
    public class BestBuyProductAPITest : BaseTest
    {

        [TestMethod]
        public void VerifyGetProductAPI()
        {
            reportUtils.CreateTestcase("Verify Get Product API - Test");

            IRestResponse restResponse = requestFactory.GetAllProduct($"{endpointUrl}/{productResource}");

            reportUtils.AddLogs(Status.Info, $"Resposne Status Code : {restResponse.StatusCode} \n" +
                $"Content : {restResponse.Content}");

            Assert.AreEqual(HttpStatusCode.OK, restResponse.StatusCode);

        }

        [TestMethod]
        public void VerifyGetProductAPIWithQueryParam()
        {
            reportUtils.CreateTestcase("Verify Get Product API with query parameter- Test");

            Dictionary<string, object> allQueryParam = new Dictionary<string, object>();

            int limit = 5;
            allQueryParam.Add("$limit", limit);

            string productEndpointUrl = $"{endpointUrl}/{productResource}";

            var restResponse = requestFactory.GetAllProduct(productEndpointUrl, allQueryParam);

            reportUtils.AddLogs(Status.Info, $"Resposne Status Code : {restResponse.StatusCode} \n" +
                $"Content : {restResponse.Content}");

            Assert.AreEqual(HttpStatusCode.OK, restResponse.StatusCode);

            Assert.AreEqual(limit, restResponse.Data.limit);

        }

        [TestMethod]
        public void VerifyAddProductApiWithStringRequestPayload()
        {

            string requestPayload = "{\r\n  \"name\": \"Samsung Mobile\",\r\n  \"type\": \"Mobile\",\r\n  \"price\": 500,\r\n  \"shipping\": 10,\r\n  \"upc\": \"Mobile\",\r\n  \"description\": \"Best Mobile in the town\",\r\n  \"manufacturer\": \"Samsung\",\r\n  \"model\": \"M31\",\r\n  \"url\": \"string\",\r\n  \"image\": \"string\"\r\n}";

            string productEndpointUrl = $"{endpointUrl}/{productResource}";
            var restResponse = requestFactory.AddProduct(productEndpointUrl, requestPayload);

            Assert.AreEqual(HttpStatusCode.Created, restResponse.StatusCode);


        }

        [TestMethod]
        public void VerifyAddProductApiWithObjectRequestPayload()
        {

            Dictionary<string, object> requestPayload = new Dictionary<string, object>();

            requestPayload.Add("name", "Samsung Mobile");
            requestPayload.Add("type", "Mobile");
            requestPayload.Add("price", 500);
            requestPayload.Add("shipping", 10);
            requestPayload.Add("upc", "Samsung Mobile");
            requestPayload.Add("description", "Best Samsung Mobile");
            requestPayload.Add("manufacturer", "Samsung Mobile");
            requestPayload.Add("model", "Samsung Mobile M21");
            requestPayload.Add("url", "Samsung Mobile");
            requestPayload.Add("image", "Samsung Mobile");


            string productEndpointUrl = $"{endpointUrl}/{productResource}";
            var restResponse = requestFactory.AddProduct(productEndpointUrl, requestPayload);

            Assert.AreEqual(HttpStatusCode.Created, restResponse.StatusCode);


        }

        [TestMethod]
        public void VerifyAddProductApiWithRequestPayloadInJsonFile()
        {

            string requestPayloadJsonFile = $"{currentProjectDirectory}/TestData/product.json";

            string requestPayload = File.ReadAllText(requestPayloadJsonFile);

            string productEndpointUrl = $"{endpointUrl}/{productResource}";
            var restResponse = requestFactory.AddProduct(productEndpointUrl, requestPayload);

            Assert.AreEqual(HttpStatusCode.Created, restResponse.StatusCode);


        }

        [TestMethod]
        public void VerifyAddProductApiWithRequestPayloadAsObject()
        {

            ProductDTO requestPayload = new ProductDTO();

            requestPayload.name = "IPhone";
            requestPayload.type = "Mobile";
            requestPayload.price = 1000;
            requestPayload.shipping = 10;
            requestPayload.upc = "2asj";
            requestPayload.description = "Iphone New Model";
            requestPayload.manufacturer = "Apple";
            requestPayload.model = "iPhone 12";
            requestPayload.url = "rweuru";
            requestPayload.image = "sdfsadfasd";


            string productEndpointUrl = $"{endpointUrl}/{productResource}";
            var restResponse = requestFactory.AddProduct(productEndpointUrl, requestPayload);

            Assert.AreEqual(HttpStatusCode.Created, restResponse.StatusCode);


        }

        [TestMethod]
        public void VerifyEditProductApiWithRequestPayloadAsObject()
        {
            string productEndpointUrl = $"{endpointUrl}/{productResource}";
            ProductDTO requestPayload = new ProductDTO();

            requestPayload.name = "IPhone";
            requestPayload.type = "Mobile";
            requestPayload.price = 1000;
            requestPayload.shipping = 10;
            requestPayload.upc = "2asj";
            requestPayload.description = "Iphone New Model";
            requestPayload.manufacturer = "Apple";
            requestPayload.model = "iPhone 12";
            requestPayload.url = "rweuru";
            requestPayload.image = "sdfsadfasd";


            
            var restResponse = requestFactory.AddProduct(productEndpointUrl, requestPayload);

            Assert.AreEqual(HttpStatusCode.Created, restResponse.StatusCode);

            int id = restResponse.Data.id;

            ProductDTO requestPayloadForUpdate = new ProductDTO();

            requestPayloadForUpdate.name = "Samsung Mobile";
            requestPayloadForUpdate.type = "Mobile";
            requestPayloadForUpdate.price = 1000;
            requestPayloadForUpdate.shipping = 10;
            requestPayloadForUpdate.upc = "2asj";
            requestPayloadForUpdate.description = "Samsung New Model";
            requestPayloadForUpdate.manufacturer = "Samsung";
            requestPayloadForUpdate.model = "Samsung 12";
            requestPayloadForUpdate.url = "rweuru";
            requestPayloadForUpdate.image = "sdfsadfasd";

            var restResponseFromEdit = requestFactory.EditProduct($"{productEndpointUrl}/{id}", requestPayloadForUpdate);

            Assert.AreEqual(HttpStatusCode.OK, restResponseFromEdit.StatusCode);

            Assert.AreEqual(requestPayloadForUpdate.name, restResponseFromEdit.Data.name);
        }

        [TestMethod]
        public void VerifyDeleteProductApiWithRequestPayloadAsObject()
        {
            string productEndpointUrl = $"{endpointUrl}/{productResource}";
            ProductDTO requestPayload = new ProductDTO();

            requestPayload.name = "IPhone";
            requestPayload.type = "Mobile";
            requestPayload.price = 1000;
            requestPayload.shipping = 10;
            requestPayload.upc = "2asj";
            requestPayload.description = "Iphone New Model";
            requestPayload.manufacturer = "Apple";
            requestPayload.model = "iPhone 12";
            requestPayload.url = "rweuru";
            requestPayload.image = "sdfsadfasd";



            var restResponse = requestFactory.AddProduct(productEndpointUrl, requestPayload);

            Assert.AreEqual(HttpStatusCode.Created, restResponse.StatusCode);

            int id = restResponse.Data.id;

            //DELETE Request

            var restResponseFromDelete = requestFactory.DeleteProduct($"{productEndpointUrl}/{id}");

            Assert.AreEqual(HttpStatusCode.OK, restResponseFromDelete.StatusCode);

            //GET Request

            var restResponseFromGet = requestFactory.GetAllProduct($"{productEndpointUrl}/{id}");

            Assert.AreEqual(HttpStatusCode.NotFound, restResponseFromGet.StatusCode);

        }
    }
}
