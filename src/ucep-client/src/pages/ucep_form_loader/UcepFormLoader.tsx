import "./UcepFormLoader.css";

import AddLocationAltOutlinedIcon from "@mui/icons-material/AddLocationAltOutlined";
import AnchorOutlinedIcon from "@mui/icons-material/AnchorOutlined";
import AttachMoneyOutlinedIcon from "@mui/icons-material/AttachMoneyOutlined";
import DirectionsBoatFilledOutlinedIcon from "@mui/icons-material/DirectionsBoatFilledOutlined";
import SummarizeOutlinedIcon from "@mui/icons-material/SummarizeOutlined";
import Form from "@rjsf/core";
import validator from "@rjsf/validator-ajv8";
import axios, { AxiosResponse } from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import Layout from "../../components/layout/Layout";
import SelectWidget from "../../components/rjsf/widgets/SelectWidget";
import {
  BaseInputTemplate,
  FieldErrorTemplate,
  FieldTemplate,
  ObjectFieldTemplate,
  SubmitButton,
} from "../../components/RjsfTemplates";
import SectionsPanel from "../../components/sections/SectionsPanel";

const globalJsonSchema = {
  name: "PersonalInfoQuestionPage",
  title: "Personal Information",
  description: "Question page for personal information.",
  type: "object",
  required: ["Person.firstName", "Person.lastName", "Person.ssn"],
  properties: {
    "Person.firstName": {
      title: "What is your First Name?",
      type: "string",
    },
    "Person.lastName": {
      title: "What is your Last Name?",
      type: "string",
    },
    "Person.ssn": {
      title: "What is your Social Insurance Number?",
      type: "string",
    },
  },
};

const globalButtonsSchema = {
  totalForms: 1,
  buttons: {
    prvious: {
      title: "Previous",
    },
    next: {
      title: "Next",
    },
  },
};

const globalUiSchema = {
  "ui:submitButtonOptions": {
    submitText: "Next",
  },
  "Person.firstName": {
    "ui:autofocus": true,
    "ui:emptyValue": "",
    "ui:autocomplete": "family-name",
  },
  "Person.lastName": {
    "ui:emptyValue": "",
    "ui:autocomplete": "given-name",
  },
  "Person.ssn": {
    "ui:emptyValue": "",
    "ui:autocomplete": "ssn",
  },
};

const UcefFormLoader = () => {
  const navigate = useNavigate();
  const [applicationName, setApplicationName] = useState(
    {} /*globalJsonSchema*/
  );
  const [currentPageName, setCurrentPageName] = useState({} /*globalUiSchema*/);
  const [scriptExecutionId, setScriptExecutionId] = useState(0);
  const [jsonSchema, setJsonSchema] = useState({} /*globalJsonSchema*/);
  const [uiSchema, setUiSchema] = useState({} /*globalUiSchema*/);
  const [formData, setFormData] = useState();
  const [prevButton, setPrevButton] = useState<boolean>(false);

  useEffect(() => {
    axios
      .post("http://localhost:9088/forms/startApplication", {
        applicationName: "MainApplication",
        scriptExecutionId: scriptExecutionId,
      })
      .then((schema) => {
        console.log(schema.data.uiSchema);
        setJsonSchema(JSON.parse(schema.data.jsonSchema));
        setUiSchema(JSON.parse(schema.data.uiSchema));
        setApplicationName(schema.data.applicationName);
        setCurrentPageName(schema.data.currentPageName);
        setScriptExecutionId(schema.data.scriptExecutionId);
      });
  }, []);

  /** Handle submission of form data. */
  const handleSubmit = ({ formData: any }: any, event: any) => {
    axios
      .post("http://localhost:9088/forms/nextActionHandler", {
        formAnswers: JSON.stringify(formData),
        applicationName: applicationName,
        currentPageName: currentPageName,
        scriptExecutionId: scriptExecutionId,
      })
      .then((schema: AxiosResponse<any>) => {
        console.log(schema.data);
        setApplicationName(schema.data.applicationName);
        setCurrentPageName(schema.data.currentPageName);
        setPrevButton(schema.data.previousButtonEnabled);
        setJsonSchema(JSON.parse(schema.data.jsonSchema));
        setUiSchema(JSON.parse(schema.data.uiSchema));
        setScriptExecutionId(schema.data.scriptExecutionId);
        setFormData(schema.data.formAnswers);
        // Redirection logic (final submission)
        //
        if (schema.data.isSubmitPage === true) {
          navigate("/success");
        }
        //
      });
  };

  /** Handle the previous button. */
  const handlePrevious = () => {
    console.log(applicationName, currentPageName, scriptExecutionId);
    axios
      .post("http://localhost:9088/forms/previousActionHandler", {
        applicationName: applicationName,
        currentPageName: currentPageName,
        scriptExecutionId: scriptExecutionId,
      })
      .then((schema) => {
        console.log(schema.data);
        setApplicationName(schema.data.applicationName);
        setCurrentPageName(schema.data.currentPageName);
        setPrevButton(schema.data.previousButtonEnabled);
        setJsonSchema(JSON.parse(schema.data.jsonSchema));
        setUiSchema(JSON.parse(schema.data.uiSchema));
        setScriptExecutionId(schema.data.scriptExecutionId);
        setFormData(schema.data.formAnswers);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <Layout>
      <div className="container mx-auto flex flex-col gap-4">
        <SectionsPanel
          list={[
            { label: "Destination", icon: AddLocationAltOutlinedIcon },
            {
              label: "Port Dues",
              icon: DirectionsBoatFilledOutlinedIcon,
            },
            { label: "Towage Dues", icon: AnchorOutlinedIcon },
            { label: "Summary", icon: SummarizeOutlinedIcon },
            { label: "Total Cost", icon: AttachMoneyOutlinedIcon },
          ]}
        />
        <Form
          className="bg-white rounded-3xl p-10 neu-form"
          schema={jsonSchema as any}
          uiSchema={{
            "ui:submitButtonOptions": {
              norender: true,
            },
          }}
          validator={validator}
          onChange={(e) => setFormData(e.formData)}
          formData={formData}
          templates={{
            ObjectFieldTemplate: ObjectFieldTemplate,
            FieldTemplate: FieldTemplate,
            FieldErrorTemplate: FieldErrorTemplate,
            BaseInputTemplate: BaseInputTemplate,
            ButtonTemplates: { SubmitButton: SubmitButton },
          }}
          widgets={{ SelectWidget: SelectWidget }}
          onSubmit={handleSubmit}
        >
          <div className="flex flex-row py-2">
            {prevButton ? (
              <button
                name="back"
                type="button"
                onClick={() => handlePrevious()}
                className={`px-6 py-2.5 bg-gradient-to-b from-appColor-600  to-gradientColor-600 text-white
                    font-medium text-xs leading-tight uppercase rounded-3xl
                    shadow-md hover:bg-blue-900 hover:shadow-lg focus:bg-blue-700
                    focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800
                    active:shadow-lg transition duration-150 ease-in-out`}
              >
                Back
              </button>
            ) : (
              <></>
            )}
            <button
              type="submit"
              className={`ml-auto px-6 py-2.5 bg-gradient-to-b from-appColor-600  to-gradientColor-600 text-white
                    font-medium text-xs leading-tight uppercase rounded-3xl
                    shadow-md hover:bg-blue-900 hover:shadow-lg focus:bg-blue-700
                    focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800
                    active:shadow-lg transition duration-150 ease-in-out`}
            >
              Next
            </button>
          </div>
        </Form>
      </div>
    </Layout>
  );
};

export default UcefFormLoader;
