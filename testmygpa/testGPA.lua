

function lua_string_split(str, split_char)
    local sub_str_tab = {};
    local i = 0;
    local j = 0;
    while true do
        j = string.find(str, split_char,i+1);    
        if j == nil then
            table.insert(sub_str_tab,str);
            break;
        end;
        table.insert(sub_str_tab,string.sub(str,i+1,j-1));
        i = j;
    end
    return sub_str_tab;
end

function getNO(key,data)
	for i=1,235 do
	local p = data[i]
	if(p[1]==key) then return(p[2]) 	end

	end
	return 0
end


y = 1  
file = io.open("1.txt" ,"r")
file2 = io.open("2.txt" ,"r")
local str = "1,2,3,4,5,6,7,8,9"  
local ta  = lua_string_split(str,",")  
local data = {}

lua_string_split(str,",")  
for line in file2:lines() do  
	local p = {}
	str = tostring(line)
	local con = lua_string_split(str," ")
	local no = lua_string_split(con[1],"/")
	table.insert(p,con[2]);
	table.insert(p,no[1]);
    --print(y ..":".. no[1])
    table.insert(data,p);  
    y = y + 1  
end  


local seq = {}
for line in file:lines() do
	local p = {id=0,no=0}
    str = tostring(line)
    local con = lua_string_split(str," ")
    p.id =  con[1]
    --print (id);
    p.no = (getNO(p.id,data))
    --print (p.no);
    table.insert(seq,p)
end

function comps(a,b)
	l = tonumber(a.no)
	r = tonumber(b.no)
return l<r
end

table.sort(seq,comps);

for key,value in pairs(seq) do  

    print(key,value.id,value.no)  
end

file:close()  
file2:close()  


