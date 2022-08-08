using BestBuyApp.Model;
using CommonLibrary.RestRequest;
using RestSharp;
using System;
using System.Collections.Generic;
using System.Text;

namespace BestBuyApp.Request
{
    public class RequestFactory
    {
        RestClientCustom restClient;

        public RequestFactory()
        {
            restClient = new RestClientCustom();
        }

        public IRestResponse GetAllProduct(string endpointUrl)
        {
            IRestRequest restRequest = new RestRequest(endpointUrl);

            IRestResponse restResponse = restClient.SendGetRequest(restRequest);
            
            return restResponse;

        }

        public IRestResponse<RootProductDTO> GetAllProduct(string endpointUrl, Dictionary<string, object> queryParam)
        {
            IRestRequest restRequest = new RestRequest(endpointUrl);

            IRestResponse<RootProductDTO> restResponse = restClient.SendGetRequest<RootProductDTO>(restRequest, queryParam);

            return restResponse;

        }

        public IRestResponse<DatumDTO> AddProduct(string endpointUrl, object requestPayload)
        {
   
            var restRequest = new RestRequest(endpointUrl);
            restRequest.AddJsonBody(requestPayload);
            
            var restResponse = restClient.SendPostRequest<DatumDTO>(restRequest,null,null);
            
            return restResponse;

        }

        public IRestResponse<DatumDTO> EditProduct(string endpointUrl, object requestPayload)
        {

            var restRequest = new RestRequest(endpointUrl);
            restRequest.AddJsonBody(requestPayload);

            var restResponse = restClient.SendPutRequest<DatumDTO>(restRequest, null, null);

            return restResponse;

        }

        public IRestResponse DeleteProduct(string endpointUrl)
        {
            var restRequest = new RestRequest(endpointUrl);

            var restResponse = restClient.SendDeleteRequest(restRequest);

            return restResponse;
        }
    }
}
