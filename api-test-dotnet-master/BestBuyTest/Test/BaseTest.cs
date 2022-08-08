using AventStack.ExtentReports;
using BestBuyApp.Request;
using CommonLibrary.Utils;
using Microsoft.Extensions.Configuration;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace BestBuyTest.Test
{
    [TestClass]
    public class BaseTest
    {
        public RequestFactory requestFactory;
        public string productResource;
        public string endpointUrl;
        public static string currentSolutionDirectory;
        public static string currentProjectDirectory;
        static IConfiguration Configuration;

        public static ExtentReportUtils reportUtils;

        public TestContext TestContext { get; set; }

        [AssemblyInitialize]
        public static void PreSetup(TestContext context)
        {
            
            Console.WriteLine("Pre Setup");

            string currentWorkingDirectory = Environment.CurrentDirectory;

            currentProjectDirectory = Directory.GetParent(currentWorkingDirectory).Parent.Parent.FullName;

            currentSolutionDirectory = Directory.GetParent(currentWorkingDirectory).Parent.Parent.Parent.FullName;

            Configuration = new ConfigurationBuilder().AddJsonFile(currentProjectDirectory + "/appSettings.json").Build();

            string reportFilename = $"{currentProjectDirectory}/Reports/restSharp-report.html";

            reportUtils = new ExtentReportUtils(reportFilename);
        }

        [TestInitialize]
        public void Setup()
        {
            requestFactory = new RequestFactory();

            productResource = "products";

            string server = Configuration["environment:server"];
            int portNumber = int.Parse(Configuration["environment:portNumber"]);

          //  reportUtils.AddLogs(Status.Info, $"Executing test cases on server : {server} and port number : {portNumber}");
            endpointUrl = $"http://{server}:{portNumber}";

            requestFactory = new RequestFactory();

            productResource = "products";
            Console.WriteLine("Setup");
        }

        [TestCleanup]
        public void PostTestExecution()
        {
         if(TestContext.CurrentTestOutcome == UnitTestOutcome.Failed)
            {
                reportUtils.AddLogs(Status.Fail, "One or more step failed");
            }
         else if(TestContext.CurrentTestOutcome == UnitTestOutcome.Aborted)
            {
                reportUtils.AddLogs(Status.Skip, "Test case aborted for some reason");
            }

        }

        

        [AssemblyCleanup]
        public static void PostCleanup()
        {
            reportUtils.DisposeExtentReport();

            Console.WriteLine("Post-Cleanup");
        }
    }
}
