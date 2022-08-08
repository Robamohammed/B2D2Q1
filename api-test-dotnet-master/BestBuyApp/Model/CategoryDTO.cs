using System;
using System.Collections.Generic;
using System.Text;

namespace BestBuyApp.Model
{
    public class CategoryDTO
    {
        public string id { get; set; }
        public string name { get; set; }
        public DateTime createdAt { get; set; }
        public DateTime updatedAt { get; set; }
    }
}
